package com.mutant.service.impl;

import com.mutant.documents.HumanDna;
import com.mutant.error.MutantApiError;
import com.mutant.exception.MutantException;
import com.mutant.model.HumanStats;
import com.mutant.repository.HumanDnaRepository;
import com.mutant.service.HumanDnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
@Service
public class HumanDnaServiceImpl implements HumanDnaService {

    @Autowired
    private HumanDnaRepository humanDnaRepository;

    @Override
    public List<HumanDna> getAll() {
        return humanDnaRepository.findAll();
    }

    @Override
    public HumanStats getGeneralStats() {
        try{
            List<HumanDna> allRecords=humanDnaRepository.findAll();
            int countMutantDNA= (int) allRecords.stream().filter(x->x.isMutant()).count();
            int countHumanDNA=allRecords.size()-countMutantDNA;
            if (countHumanDNA==0){
                throw new IllegalArgumentException(MutantApiError.MUTANT_API_ERROR_NO_HUMAN_TO_GENERATE_RATIO.getMessage());
            }
            Double ratio= Double.valueOf(countMutantDNA/countHumanDNA);
            return HumanStats.builder()
                    .countMutantDNA(countMutantDNA)
                    .countHumanDNA(countHumanDNA)
                    .ratio(ratio)
                    .build();
        } catch (Exception e){
            throw new MutantException(MutantApiError.MUTANT_API_ERROR_HERROR_GENERATING_STATS, e);
        }
    }

    @Override
    public Optional<HumanDna> getById(String id) {
        return Optional.empty();
    }

    @Override
    public HumanDna create(HumanDna humanDna) {
        try{
            validateHumanDna(humanDna);
            int equalSecuenceCounter = getEqualsSecuence(humanDna.getDna().toArray(new String[0]));
            if (equalSecuenceCounter >1 ? true : false) {
                humanDna.setMutant(true);
                humanDnaRepository.save(humanDna);
                return humanDna;
            } else {
                throw new NoSuchElementException(MutantApiError.MUTANT_API_ERROR_IS_NOT_MUTANT.getMessage());
            }

        } catch (IllegalArgumentException e){
            throw new MutantException(MutantApiError.MUTANT_API_GENERAL_PARAM_ERROR, e);
        } catch (NullPointerException e){
            throw new MutantException(MutantApiError.MUTANT_API_NULL_POINT.getMessage(),e);
        } catch (NoSuchElementException e){
            throw new MutantException(MutantApiError.MUTANT_API_ERROR_IS_NOT_MUTANT,e);
        }

    }

    @Override
    public HumanDna update(String id, HumanDna humanDna) {
        return null;
    }

    /**
     *
     * @param dna: List of dna secuence
     * @return
     */
    private int getEqualsSecuence(String[] dna) {
        int dnaEqualSecuenceCounter = 0;
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[i].length(); j++) {
                if (j < dna[i].length() - 3) {
                    if (isEqual(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2), dna[i].charAt(j + 3))) {
                        dnaEqualSecuenceCounter++;
                    }
                }
                if (i < dna.length - 3) {
                    // vertical check
                    if (isEqual(dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j), dna[i + 3].charAt(j))) {
                        dnaEqualSecuenceCounter++;
                    }
                }
                if (i < dna.length - 3 && j < dna[i].length() - 3) {
                    if (isEqual(dna[i].charAt(j), dna[i + 1].charAt(j + 1), dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3))) {
                        dnaEqualSecuenceCounter++;
                    }
                }
                if (i >= 3 && j < dna[i].length() - 3) {
                    if (isEqual(dna[i].charAt(j), dna[i - 1].charAt(j + 1), dna[i - 2].charAt(j + 2), dna[i - 3].charAt(j + 3))) {
                        dnaEqualSecuenceCounter++;
                    }
                }

            }

        }
        return dnaEqualSecuenceCounter;
    }

    /**
     *  Chect if all in the dna secuence are equals
     * @param a1
     * @param a2
     * @param a3
     * @param a4
     * @return true if are equas
     */
    private boolean isEqual(char a1, char a2, char a3, char a4) {
        return a1 == a2 && a2 == a3 && a3 == a4;
    }

    /**
     * Method used as utility to check a expresion and throw an exception
     * @param expression expresion to evalue
     * @param errorMessage erro message to throw
     */
    private void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    /**
     *
     * @param humanDna: Validate if the DNA is not null
     */
    private void validateHumanDna(HumanDna humanDna){
        Optional.ofNullable(humanDna.getDna())
                .orElseThrow(()->new IllegalArgumentException(MutantApiError.MUTANT_API_ERROR_NULL_DNA.getMessage()));
    }
}
