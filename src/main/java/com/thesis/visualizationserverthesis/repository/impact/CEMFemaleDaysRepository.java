package com.thesis.visualizationserverthesis.repository.impact;

import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesFilter;
import com.thesis.visualizationserverthesis.model.entity.impact.CEMFemaleDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CEMFemaleDaysRepository extends JpaRepository<CEMFemaleDays,Long> {

    @Query(value="select c.* from cem_female_victim_days c " +
            "where c.case_date >= :startDate and c.case_date <= :lastDate " +
            "and ( :#{#filter.state} is null or :#{#filter.state} = c.state_peru ) " +
            "and ( :#{#filter.province} is null or :#{#filter.province} = c.province_peru ) " +
            "and ( :#{#filter.district} is null or :#{#filter.district} = c.district_peru ) " +
            "order by c.case_date asc", nativeQuery = true)
    List<CEMFemaleDays> searchCasesForImpact(@Param("filter")ImpactViolenceCasesFilter filter,@Param("startDate") LocalDate startDate,@Param("lastDate") LocalDate lastDate);
}
