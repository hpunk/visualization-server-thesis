package com.thesis.visualizationserverthesis.model.api;

import com.thesis.visualizationserverthesis.utils.AggregatedAppAssistants;
import com.thesis.visualizationserverthesis.utils.PreventiveActionCounter;
import lombok.Data;

import java.util.List;

@Data
public class PreventiveActionSearchResponse {
    private List<PreventiveActionCounter> preventiveActions;
    private AggregatedAppAssistants aggregatedAssistants;
}
