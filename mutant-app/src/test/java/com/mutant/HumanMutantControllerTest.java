package com.mutant;

import com.google.gson.Gson;
import com.mutant.controller.HumanMutantController;
import com.mutant.documents.HumanDna;
import com.mutant.mapper.DtoMapper;
import com.mutant.mapper.MutantMapper;
import com.mutant.mapper.StatsMapper;
import com.mutant.model.HumanStats;
import com.mutant.service.HumanDnaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class HumanMutantControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private HumanMutantController humanMutantController;

    @Mock
    private MutantMapper humanMapper;

    @Mock
    private StatsMapper statsMapper;

    @Mock
    private HumanDnaService humanDnaService;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(humanMutantController).build();
    }

    @Test
    public void test_getStats() throws Exception {

        //WHEN
        Mockito.when(humanDnaService.getGeneralStats()).thenReturn(new HumanStats(1,1,0.1));
        Mockito.when(statsMapper.mapToDto(any())).thenReturn(new HumanStatsDto(1,1,0.1));
        final ResultActions result = this.mockMvc.perform(get("/stats")
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
        );
        //THEN
        result.andExpect(status().isOk());
        result.andExpect(content().string(containsString("1")));
    }

    @Test
    public void test_post_resultOk() throws Exception {
        List<String> dnaToEvaluate = new ArrayList<String>(){{
            add("ATGCGA");
            add("CAGTGC");
            add("TTATGT");
            add("AGAAGG");
            add("CCCCTA");
            add("TCACTG");
        }};
        HumanDna humanDna = HumanDna.builder()
                .id("gtmls")
                .dna(dnaToEvaluate)
                .mutant(false)
                .build();

        HumanDnaDto humanDnaDto = HumanDnaDto.builder()
                .dna(dnaToEvaluate)
                .mutant(false)
                .build();
        Gson gson = new Gson();
        String json = gson.toJson(humanDnaDto);

    }




}
