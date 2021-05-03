package com.thesis.visualizationserverthesis.controller;

import com.thesis.visualizationserverthesis.model.api.CasesByUbigeoForMonth;
import com.thesis.visualizationserverthesis.model.api.DetailedCasesByWeek;
import com.thesis.visualizationserverthesis.model.api.EvolutionCasesByMonthFilter;
import com.thesis.visualizationserverthesis.model.api.EvolutionDetailedCasesFilter;
import com.thesis.visualizationserverthesis.service.EvolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class EvolutionController {

    @Autowired
    private EvolutionService service;

    @GetMapping("/evolution/cases-by-month")
    public List<CasesByUbigeoForMonth> getCasesByUbigeoForMonth(@RequestBody EvolutionCasesByMonthFilter filter){
        return service.getCasesByUbigeoForMonth(filter);
    }

    @GetMapping("/evolution/cases-by-week")
    public List<DetailedCasesByWeek> getDetailedCasesByWeek(@RequestBody EvolutionDetailedCasesFilter filter){
        return service.getDetailedCasesByWeek(filter);
    }
}
