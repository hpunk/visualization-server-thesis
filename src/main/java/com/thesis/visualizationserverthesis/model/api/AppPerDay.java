package com.thesis.visualizationserverthesis.model.api;

import com.thesis.visualizationserverthesis.utils.PreventiveActionCounter;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AppPerDay {
    private LocalDate date;
    private Integer count;
    private List<PreventiveActionCounter> preventiveActions;
}
