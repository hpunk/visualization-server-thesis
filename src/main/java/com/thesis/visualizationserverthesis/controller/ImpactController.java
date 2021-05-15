package com.thesis.visualizationserverthesis.controller;

import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesDTO;
import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesFilter;
import com.thesis.visualizationserverthesis.model.api.PreventiveActionSearchResponse;
import com.thesis.visualizationserverthesis.model.api.PreventiveActionsFilter;
import com.thesis.visualizationserverthesis.service.ImpactService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class ImpactController extends BaseController{
    @Autowired
    private ImpactService impactService;

    @GetMapping("/impact/app")
    public PreventiveActionSearchResponse searchPreventiveActions(@RequestParam Map<String,Object> searchParams){
        val filter = mapQueryTo(searchParams, PreventiveActionsFilter.class);
        return impactService.searchPreventiveActions(filter);
    }

    @GetMapping("/impact/violence-cases")
    public ImpactViolenceCasesDTO getViolenceCasesForImpact(@RequestParam Map<String,Object> searchParams){
        val filter = mapQueryTo(searchParams, ImpactViolenceCasesFilter.class);
        return impactService.getViolenceCasesForImpact(filter);
    }
}
