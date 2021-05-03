package com.thesis.visualizationserverthesis.repository;

import com.thesis.visualizationserverthesis.model.entity.WeekDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface WeekDateRepository extends JpaRepository<WeekDate,Long> {

    @Query(value="select w.week_id from week_date w where :dateParam >= w.date_start and :dateParam <= w.date_end", nativeQuery = true)
    Long getWeekFromDate(@Param("dateParam") LocalDate date);
}
