package com.mutant;

import lombok.Builder;
import lombok.Value;

/**
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
@Value
@Builder
public class HumanStatsDto {
    private int countMutantDNA;
    private int countHumanDNA;
    private Double ratio;
}
