package com.thesis.visualizationserverthesis.model.api;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EvolutionDetailedCasesFilter {
    private LocalDate startDate;
    private LocalDate endDate;
    private String filterBy;
    private Long state;
    private Long province;
}
