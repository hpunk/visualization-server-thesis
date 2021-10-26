package com.thesis.visualizationserverthesis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.visualizationserverthesis.model.api.*;
import com.thesis.visualizationserverthesis.service.ImpactService;
import lombok.val;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ImpactController extends BaseController{

    private final ImpactService impactService;

    public ImpactController(ObjectMapper mapper, ImpactService impactService){
        super(mapper);
        this.impactService = impactService;
    }

    @GetMapping("/impact/app")
    public PreventiveActionSearchResponse searchPreventiveActions(@RequestParam Map<String,Object> searchParams){
        val filter = mapQueryTo(searchParams, PreventiveActionsFilter.class);
        return impactService.searchPreventiveActions(filter);
    }

    @GetMapping("/impact/violence-cases")
    public ImpactViolenceCasesDTO getViolenceCasesForImpact(@RequestParam Map<String,Object> searchParams) throws ParseException {
        val filter = mapQueryTo(searchParams, ImpactViolenceCasesFilter.class);
        return impactService.getViolenceCasesForImpact(filter);
    }

    @GetMapping("/impact/app/per-day")
    public List<AppPerDay> getAppPerDay(@RequestParam Map<String,Object> searchParams){
        val filter = mapQueryTo(searchParams, PreventiveActionsFilter.class);
        return impactService.getAppPerDay(filter);
    }
}
