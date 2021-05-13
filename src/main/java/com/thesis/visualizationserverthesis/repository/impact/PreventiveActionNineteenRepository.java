package com.thesis.visualizationserverthesis.repository.impact;

import com.thesis.visualizationserverthesis.model.api.PreventiveActionsFilter;
import com.thesis.visualizationserverthesis.model.entity.impact.PreventiveActionNineteen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreventiveActionNineteenRepository extends JpaRepository<PreventiveActionNineteen,Long> {

    @Query(value ="select p.* from app_2019 p " +
            "where DATE(p.start_date) >= DATE(:#{#filter.startDate}) AND DATE(p.start_date) <= DATE(:#{#filter.endDate}) " +
            "and (:#{#filter.state} = 0 or (:#{#filter.state} = p.state_code) ) " +
            "and (:#{#filter.province} = 0  or (:#{#filter.province} = p.province_code )) " +
            "and (:#{#filter.district} = 0 or (:#{#filter.district} = p.district_code )) " +
            "order by p.start_date desc" , nativeQuery = true)
    List<PreventiveActionNineteen> searchPreventiveActions(@Param("filter") PreventiveActionsFilter filter);
}
