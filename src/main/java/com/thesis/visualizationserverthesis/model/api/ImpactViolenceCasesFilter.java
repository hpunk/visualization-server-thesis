package com.thesis.visualizationserverthesis.model.api;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ImpactViolenceCasesFilter {
    private Long state;
    private Long province;
    private Long district;
    private LocalDate appDate;
    private Integer daysBefore;
    private Integer daysAfter;
}
