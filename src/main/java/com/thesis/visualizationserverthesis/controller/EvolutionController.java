package com.thesis.visualizationserverthesis.controller;

import com.thesis.visualizationserverthesis.model.api.CasesByUbigeoForMonth;
import com.thesis.visualizationserverthesis.model.api.DetailedCasesByWeek;
import com.thesis.visualizationserverthesis.model.api.EvolutionCasesByMonthFilter;
import com.thesis.visualizationserverthesis.model.api.EvolutionDetailedCasesFilter;
import com.thesis.visualizationserverthesis.service.EvolutionService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class EvolutionController extends BaseController{

    @Autowired
    private EvolutionService service;

    @GetMapping("/evolution/cases-by-month")
    public List<CasesByUbigeoForMonth> getCasesByUbigeoForMonth(@RequestParam Map<String,Object> searchParams){
        val filter = mapQueryTo(searchParams, EvolutionCasesByMonthFilter.class);
        return service.getCasesByUbigeoForMonth(filter);
    }

    @GetMapping("/evolution/cases-by-week")
    public List<DetailedCasesByWeek> getDetailedCasesByWeek(@RequestParam Map<String,Object> searchParams){
        val filter = mapQueryTo(searchParams, EvolutionDetailedCasesFilter.class);
        return service.getDetailedCasesByWeek(filter);
    }
}
