package com.thesis.visualizationserverthesis.repository.impact;

import com.thesis.visualizationserverthesis.model.api.EvolutionCasesByMonthFilter;
import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesFilter;
import com.thesis.visualizationserverthesis.model.entity.impact.CEMFemaleDays;
import com.thesis.visualizationserverthesis.utils.IUbigeoCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CEMFemaleDaysRepository extends JpaRepository<CEMFemaleDays,Long> {

    @Query(value="select c.* from cem_female_victim_days c " +
            "where c.case_date >= :startDate and c.case_date <= :lastDate " +
            "and ( :#{#filter.state} = 0 or :#{#filter.state} = c.state_peru ) " +
            "and ( :#{#filter.province} = 0 or :#{#filter.province} = c.province_peru ) " +
            "and ( :#{#filter.district} = 0 or :#{#filter.district} = c.district_peru ) " +
            "order by c.case_date asc", nativeQuery = true)
    List<CEMFemaleDays> searchCasesForImpact(@Param("filter")ImpactViolenceCasesFilter filter,@Param("startDate") LocalDate startDate,@Param("lastDate") LocalDate lastDate);

    @Query(value="select c.province_peru as ubigeo, sum(c.physical_v_count)+sum(c.sexual_v_count)+sum(c.economical_v_count)+sum(c.psychological_v_count) as count " +
            "from cem_female_victim_days c " +
            "where c.case_date >= :firstDate and c.case_date < :lastDate " +
            "and :#{#filter.state} = c.state_peru " +
            "group by c.province_peru " +
            "order by c.province_peru asc", nativeQuery = true)
    List<IUbigeoCountProjection> aggregateByProvince(@Param("firstDate") LocalDate start, @Param("lastDate") LocalDate end, @Param("filter") EvolutionCasesByMonthFilter filter);

    @Query(value="select c.state_peru as ubigeo, sum(c.physical_v_count)+sum(c.sexual_v_count)+sum(c.economical_v_count)+sum(c.psychological_v_count) as count " +
            "from cem_female_victim_days c " +
            "where c.case_date >= :firstDate and c.case_date < :lastDate " +
            "group by c.state_peru " +
            "order by c.state_peru asc", nativeQuery = true)
    List<IUbigeoCountProjection> aggregateByState(@Param("firstDate") LocalDate start,@Param("lastDate") LocalDate end);
}
