package com.thesis.visualizationserverthesis.model.api;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PreventiveActionsFilter {
    private Long state;
    private Long province;
    private Long district;
    private LocalDate startDate;
    private LocalDate endDate;
}
