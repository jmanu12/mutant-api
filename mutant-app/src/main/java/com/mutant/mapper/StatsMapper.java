package com.mutant.mapper;

import com.mutant.HumanDnaDto;
import com.mutant.HumanStatsDto;
import com.mutant.documents.HumanDna;
import com.mutant.model.HumanStats;
import org.mapstruct.Mapper;

/**
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
@Mapper
public interface StatsMapper extends DtoMapper<HumanStats, HumanStatsDto, HumanStatsDto> {
}
