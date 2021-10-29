package com.mutant.controller;

import com.mutant.HumanDnaDto;
import com.mutant.HumanStatsDto;
import com.mutant.documents.HumanDna;
import com.mutant.mapper.MutantMapper;
import com.mutant.mapper.StatsMapper;
import com.mutant.service.HumanDnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
@RestController
@RequestMapping("/")
public class HumanMutantController {

    @Autowired
    private HumanDnaService humanDnaService;

    @Autowired
    private MutantMapper humanMapper;

    @Autowired
    private StatsMapper statsMapper;


    @GetMapping(path = "/all")
    public ResponseEntity<List<HumanDnaDto>> getAll(){
        return ResponseEntity.ok(humanDnaService.getAll().stream().map(humanMapper::mapToDto).collect(Collectors.toList()));
    }

    @PostMapping(path = "/")
    public ResponseEntity<HumanDnaDto> post(
            @RequestBody HumanDnaDto body) {
        final HumanDna humanDna = humanDnaService.create(humanMapper.mapToDocument(body));
        return ResponseEntity.ok(humanMapper.mapToDto(humanDna));
    }

    @GetMapping(path = "/stats")
    public ResponseEntity<HumanStatsDto> portfolioSummary( ){
        return ResponseEntity.ok((HumanStatsDto) statsMapper.mapToDto(humanDnaService.getGeneralStats()));
    }

}
