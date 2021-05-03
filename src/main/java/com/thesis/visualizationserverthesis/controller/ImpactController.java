package com.thesis.visualizationserverthesis.controller;

import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesDTO;
import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesFilter;
import com.thesis.visualizationserverthesis.model.api.PreventiveActionsFilter;
import com.thesis.visualizationserverthesis.model.entity.base.PreventiveAction;
import com.thesis.visualizationserverthesis.service.ImpactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ImpactController {
    @Autowired
    private ImpactService impactService;

    @GetMapping("/impact/app")
    public List<PreventiveAction> searchPreventiveActions(@RequestBody PreventiveActionsFilter filter){
        return impactService.searchPreventiveActions(filter);
    }

    @GetMapping("/impact/violence-cases")
    public ImpactViolenceCasesDTO getViolenceCasesForImpact(@RequestBody ImpactViolenceCasesFilter filter){
        return impactService.getViolenceCasesForImpact(filter);
    }
}
