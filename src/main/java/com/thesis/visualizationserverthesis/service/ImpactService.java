package com.thesis.visualizationserverthesis.service;

import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesDTO;
import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesFilter;
import com.thesis.visualizationserverthesis.model.api.PreventiveActionSearchResponse;
import com.thesis.visualizationserverthesis.model.api.PreventiveActionsFilter;

public interface ImpactService {
    PreventiveActionSearchResponse searchPreventiveActions(PreventiveActionsFilter filter);
    ImpactViolenceCasesDTO getViolenceCasesForImpact(ImpactViolenceCasesFilter filter);
}
