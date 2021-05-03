package com.thesis.visualizationserverthesis.service;

import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesDTO;
import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesFilter;
import com.thesis.visualizationserverthesis.model.api.PreventiveActionsFilter;
import com.thesis.visualizationserverthesis.model.entity.base.PreventiveAction;

import java.util.List;

public interface ImpactService {
    List<PreventiveAction> searchPreventiveActions(PreventiveActionsFilter filter);
    ImpactViolenceCasesDTO getViolenceCasesForImpact(ImpactViolenceCasesFilter filter);
}
