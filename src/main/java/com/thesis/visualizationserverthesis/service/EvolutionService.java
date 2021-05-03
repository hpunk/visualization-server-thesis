package com.thesis.visualizationserverthesis.service;

import com.thesis.visualizationserverthesis.model.api.CasesByUbigeoForMonth;
import com.thesis.visualizationserverthesis.model.api.DetailedCasesByWeek;
import com.thesis.visualizationserverthesis.model.api.EvolutionCasesByMonthFilter;
import com.thesis.visualizationserverthesis.model.api.EvolutionDetailedCasesFilter;

import java.util.List;

public interface EvolutionService {
    List<CasesByUbigeoForMonth> getCasesByUbigeoForMonth(EvolutionCasesByMonthFilter filter);
    List<DetailedCasesByWeek> getDetailedCasesByWeek(EvolutionDetailedCasesFilter filter);
}
