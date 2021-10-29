package com.mutant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HumanStats implements Serializable {
    private int countMutantDNA;
    private int countHumanDNA;
    private Double ratio;
}
