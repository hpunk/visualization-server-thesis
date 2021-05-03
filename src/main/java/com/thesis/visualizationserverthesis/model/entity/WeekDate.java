package com.thesis.visualizationserverthesis.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name="week_date")
public class WeekDate {
    @Id
    @Column(name = "week_id")
    private Long weekId;
    @Column(name = "date_start")
    private LocalDate startDate;
    @Column(name = "date_end")
    private LocalDate endDate;
}
