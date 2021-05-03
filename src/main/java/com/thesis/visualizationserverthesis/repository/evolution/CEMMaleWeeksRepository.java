package com.thesis.visualizationserverthesis.repository.evolution;

import com.thesis.visualizationserverthesis.model.api.EvolutionDetailedCasesFilter;
import com.thesis.visualizationserverthesis.model.entity.evolution.CEMMaleWeeks;
import com.thesis.visualizationserverthesis.utils.ICEMWeeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CEMMaleWeeksRepository extends JpaRepository<CEMMaleWeeks,Long> {

    @Query(value="select c.week as week, c.state_peru as ubigeo, " +
            "sum(c.physical_v_count) as physical, sum(c.economical_v_count) as economical, sum(c.sexual_v_count) as sexual, " +
            "sum(c.psychological_v_count) as psychological, sum(c.first_time_count) as firstTime, sum(c.inf_count) as inf, " +
            "sum(c.nin_count) as nin, sum(c.ado_count) as adol, sum(c.ado_t_count) as adolT, sum(c.jov_count) as jov, " +
            "sum(c.adu_count) as adul, sum(c.m60_count) as mayo, sum(c.family_count) as family, sum(c.love_count) as love, " +
            "sum(c.no_rel_count) as noRelation, sum(c.aggr_alcohol_count) as aggrAlcohol, sum(c.aggr_drug_count) as aggrDrug, " +
            "sum(c.vict_disab_count) as victDisab, sum(c.vict_alco_count) as victAlcohol, sum(c.vict_drug_count) as victDrug, " +
            "sum(c.vict_lgtbi_count) as victLgtbi " +
            "from cem_male_victim_weeks c " +
            "where c.week = :week and c.state_peru = :#{#filter.state} " +
            "group by c.week, c.state_peru " +
            "order by c.week asc", nativeQuery = true)
    Optional<ICEMWeeks> getAggregatedForWeekAndState(@Param("week") Long week, @Param("filter") EvolutionDetailedCasesFilter filter);

    @Query(value="select c.week as week, c.province_peru as ubigeo, " +
            "sum(c.physical_v_count) as physical, sum(c.economical_v_count) as economical, sum(c.sexual_v_count) as sexual, " +
            "sum(c.psychological_v_count) as psychological, sum(c.first_time_count) as firstTime, sum(c.inf_count) as inf, " +
            "sum(c.nin_count) as nin, sum(c.ado_count) as adol, sum(c.ado_t_count) as adolT, sum(c.jov_count) as jov, " +
            "sum(c.adu_count) as adul, sum(c.m60_count) as mayo, sum(c.family_count) as family, sum(c.love_count) as love, " +
            "sum(c.no_rel_count) as noRelation, sum(c.aggr_alcohol_count) as aggrAlcohol, sum(c.aggr_drug_count) as aggrDrug, " +
            "sum(c.vict_disab_count) as victDisab, sum(c.vict_alco_count) as victAlcohol, sum(c.vict_drug_count) as victDrug, " +
            "sum(c.vict_lgtbi_count) as victLgtbi " +
            "from cem_male_victim_weeks c " +
            "where c.week = :week and c.province_peru = :#{#filter.province} " +
            "group by c.week, c.province_peru " +
            "order by c.week asc", nativeQuery = true)
    Optional<ICEMWeeks> getAggregatedForWeekAndProvince(@Param("week") Long week, @Param("filter") EvolutionDetailedCasesFilter filter);

}
