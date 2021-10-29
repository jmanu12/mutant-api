package com.mutant;

import com.mutant.config.HumanDnaServiceTestConfig;
import com.mutant.documents.HumanDna;
import com.mutant.error.MutantApiError;
import com.mutant.exception.MutantException;
import com.mutant.model.HumanStats;
import com.mutant.repository.HumanDnaRepository;
import com.mutant.service.HumanDnaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
@ExtendWith(SpringExtension.class)
@Import({ HumanDnaServiceTestConfig.class })
public class HumanDnaServiceTest {
    @Autowired
    private HumanDnaService humanDnaService;

    @MockBean
    private HumanDnaRepository humanDnaRepository;

    @Test
    public void testGetAllResultOk(){
        HumanDna humanDna = HumanDna.builder()
                    .id("gtmls")
                    .dna(new ArrayList<String>(){{
                        add("ATGCGA");
                        add("CAGTGC");
                        add("TTATGT");
                        add("AGAAGG");
                        add("CCCCTA");
                        add("TCACTG");
                    }})
                    .mutant(false)
                    .build();
        List<HumanDna> humanDnaList = new ArrayList<HumanDna>(){
            {
                add(humanDna);
            }
        };
        Mockito.when(humanDnaRepository.findAll()).thenReturn(humanDnaList);
        List<HumanDna> result= humanDnaService.getAll();
        assertEquals(result, humanDnaList);
    }

    @Test
    public void testGenerateStatsResultOk(){
        //mutant false
        HumanDna humanDna1 = HumanDna.builder()
                .id("gtmls")
                .dna(new ArrayList<String>(){{
                    add("ATGCGA");
                    add("CAGTGC");
                    add("TTATGT");
                    add("AGAAGG");
                    add("CCCCTA");
                    add("TCACTG");
                }})
                .mutant(false)
                .build();
        // mutant true
        HumanDna humanDna2 = HumanDna.builder()
                .id("gtmls")
                .dna(new ArrayList<String>(){{
                    add("ATGCGA");
                    add("CAGTGC");
                    add("TTATGT");
                    add("AGAAGG");
                    add("CCCCTA");
                    add("TCACTG");
                }})
                .mutant(true)
                .build();
        List<HumanDna> humanDnaList = new ArrayList<HumanDna>(){
            {
                add(humanDna1);
                add(humanDna2);
            }
        };
        Mockito.when(humanDnaRepository.findAll()).thenReturn(humanDnaList);
        HumanStats result= humanDnaService.getGeneralStats();
        //ratio 1, mutant 1, human1
        assertEquals(result.getRatio(), 1);
        assertEquals(result.getCountHumanDNA(), 1);
        assertEquals(result.getCountMutantDNA(), 1);
    }
    @Test
    public void testGenerateStatsNoHumanResultException(){
        Throwable exception = assertThrows(MutantException.class, () -> {
            // mutant true
            HumanDna humanDna2 = HumanDna.builder()
                    .id("gtmls")
                    .dna(new ArrayList<String>(){{
                        add("ATGCGA");
                        add("CAGTGC");
                        add("TTATGT");
                        add("AGAAGG");
                        add("CCCCTA");
                        add("TCACTG");
                    }})
                    .mutant(true)
                    .build();
            List<HumanDna> humanDnaList = new ArrayList<HumanDna>(){
                {
                    add(humanDna2);
                }
            };
            Mockito.when(humanDnaRepository.findAll()).thenReturn(humanDnaList);
            HumanStats result= humanDnaService.getGeneralStats();
        });

    }

    @Test
    public void testCreateResultOkMutantTrue(){
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

        Mockito.when(humanDnaRepository.save(any())).thenReturn(humanDna);
        HumanDna result= humanDnaService.create(humanDna);
        assertEquals(result.isMutant(), true);
    }

    @Test
    public void testCreateResultForbiddenMutant(){
        Throwable exception = assertThrows(MutantException.class, () -> {
            List<String> dnaToEvaluate = new ArrayList<String>(){{
                add("TTGCGA");
                add("CAGTGC");
                add("TTATGT");
                add("AGAAGG");
                add("ECCCTA");
                add("TCACTG");
            }};
            HumanDna humanDna = HumanDna.builder()
                    .id("gtmls")
                    .dna(dnaToEvaluate)
                    .mutant(false)
                    .build();

            Mockito.when(humanDnaRepository.save(any())).thenReturn(humanDna);
            HumanDna result= humanDnaService.create(humanDna);

        });
        assertEquals(MutantApiError.MUTANT_API_ERROR_IS_NOT_MUTANT.getMessage(), exception.getMessage());
    }

    @Test
    public void testCreateResultFalse(){
        Throwable exception = assertThrows(MutantException.class, () -> {
            HumanDna humanDna = HumanDna.builder()
                    .id("gtmls")
                    .dna(null)
                    .mutant(false)
                    .build();

            Mockito.when(humanDnaRepository.save(any())).thenReturn(humanDna);
            HumanDna result= humanDnaService.create(humanDna);

        });
        assertEquals(MutantApiError.MUTANT_API_GENERAL_PARAM_ERROR.getMessage(), exception.getMessage());
    }



}
