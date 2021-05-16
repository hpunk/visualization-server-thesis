package com.thesis.visualizationserverthesis.repository.impact;

import com.thesis.visualizationserverthesis.model.api.EvolutionCasesByMonthFilter;
import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesFilter;
import com.thesis.visualizationserverthesis.model.entity.impact.CEMMaleDays;
import com.thesis.visualizationserverthesis.utils.ICemCountImpact;
import com.thesis.visualizationserverthesis.utils.IUbigeoCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CEMMaleDaysRepository extends JpaRepository<CEMMaleDays,Long> {

    @Query(value="select c.case_date as caseDate, SUM(c.physical_v_count) as physicalVCount, SUM(c.economical_v_count) as economicalVCount, SUM(c.sexual_v_count) as sexualVCount, " +
            " SUM(c.psychological_v_count) as psychologicalVCount from cem_male_victim_days c " +
            "where c.case_date >= :startDate and c.case_date <= :lastDate " +
            "group by c.case_date " +
            "order by c.case_date asc", nativeQuery = true)
    List<ICemCountImpact> searchCasesForImpact(@Param("startDate") LocalDate startDate, @Param("lastDate") LocalDate lastDate);

    @Query(value="select c.case_date as caseDate, c.district_peru as districtCode, SUM(c.physical_v_count) as physicalVCount, SUM(c.economical_v_count) as economicalVCount, SUM(c.sexual_v_count) as sexualVCount, " +
            " SUM(c.psychological_v_count) as psychologicalVCount from cem_male_victim_days c " +
            "where c.case_date >= :startDate and c.case_date <= :lastDate " +
            "and ( :#{#filter.state} = 0 or :#{#filter.state} = c.state_peru ) " +
            "and ( :#{#filter.province} = 0 or :#{#filter.province} = c.province_peru ) " +
            "and ( :#{#filter.district} = 0 or :#{#filter.district} = c.district_peru ) " +
            "group by c.case_date, c.district_peru " +
            "order by c.case_date asc", nativeQuery = true)
    List<ICemCountImpact> searchCasesForImpactDistrict(@Param("filter") ImpactViolenceCasesFilter filter,@Param("startDate") LocalDate startDate,@Param("lastDate") LocalDate lastDate);

    @Query(value="select c.case_date as caseDate, c.province_peru as provinceCode, SUM(c.physical_v_count) as physicalVCount, SUM(c.economical_v_count) as economicalVCount, SUM(c.sexual_v_count) as sexualVCount, " +
            " SUM(c.psychological_v_count) as psychologicalVCount from cem_male_victim_days c " +
            "where c.case_date >= :startDate and c.case_date <= :lastDate " +
            "and ( :#{#filter.state} = 0 or :#{#filter.state} = c.state_peru ) " +
            "and ( :#{#filter.province} = 0 or :#{#filter.province} = c.province_peru ) " +
            "group by c.case_date, c.province_peru " +
            "order by c.case_date asc", nativeQuery = true)
    List<ICemCountImpact> searchCasesForImpactProvince(@Param("filter") ImpactViolenceCasesFilter filter,@Param("startDate") LocalDate startDate,@Param("lastDate") LocalDate lastDate);

    @Query(value="select c.case_date as caseDate, c.state_peru as stateCode, SUM(c.physical_v_count) as physicalVCount, SUM(c.economical_v_count) as economicalVCount, SUM(c.sexual_v_count) as sexualVCount, " +
            " SUM(c.psychological_v_count) as psychologicalVCount from cem_male_victim_days c " +
            "where c.case_date >= :startDate and c.case_date <= :lastDate " +
            "and ( :#{#filter.state} = 0 or :#{#filter.state} = c.state_peru ) " +
            "group by c.case_date, c.state_peru " +
            "order by c.case_date asc", nativeQuery = true)
    List<ICemCountImpact> searchCasesForImpactState(@Param("filter") ImpactViolenceCasesFilter filter,@Param("startDate") LocalDate startDate,@Param("lastDate") LocalDate lastDate);

    @Query(value="select c.province_peru as ubigeo, sum(c.physical_v_count)+sum(c.sexual_v_count)+sum(c.economical_v_count)+sum(c.psychological_v_count) as count " +
            "from cem_male_victim_days c " +
            "where c.case_date >= :firstDate and c.case_date < :lastDate " +
            "and :#{#filter.state} = c.state_peru " +
            "group by c.province_peru " +
            "order by c.province_peru asc", nativeQuery = true)
    List<IUbigeoCountProjection> aggregateByProvince(@Param("firstDate") LocalDate start, @Param("lastDate") LocalDate end, @Param("filter") EvolutionCasesByMonthFilter filter);

    @Query(value="select c.state_peru as ubigeo, sum(c.physical_v_count)+sum(c.sexual_v_count)+sum(c.economical_v_count)+sum(c.psychological_v_count) as count " +
            "from cem_male_victim_days c " +
            "where c.case_date >= :firstDate and c.case_date < :lastDate " +
            "group by c.state_peru " +
            "order by c.state_peru asc", nativeQuery = true)
    List<IUbigeoCountProjection> aggregateByState(@Param("firstDate") LocalDate start, @Param("lastDate") LocalDate end);
}
