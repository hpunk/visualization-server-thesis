package com.thesis.visualizationserverthesis.service;

import com.thesis.visualizationserverthesis.model.api.*;

import java.text.ParseException;
import java.util.List;

public interface ImpactService {
    PreventiveActionSearchResponse searchPreventiveActions(PreventiveActionsFilter filter);
    ImpactViolenceCasesDTO getViolenceCasesForImpact(ImpactViolenceCasesFilter filter) throws ParseException;
    List<AppPerDay> getAppPerDay(PreventiveActionsFilter filter);
}
