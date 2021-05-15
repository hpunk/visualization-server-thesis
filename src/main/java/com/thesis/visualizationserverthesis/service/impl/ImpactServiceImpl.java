package com.thesis.visualizationserverthesis.service.impl;

import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesDTO;
import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesFilter;
import com.thesis.visualizationserverthesis.model.api.PreventiveActionSearchResponse;
import com.thesis.visualizationserverthesis.model.api.PreventiveActionsFilter;
import com.thesis.visualizationserverthesis.model.entity.impact.*;
import com.thesis.visualizationserverthesis.repository.impact.*;
import com.thesis.visualizationserverthesis.service.ImpactService;
import com.thesis.visualizationserverthesis.utils.AggregatedAppAssistants;
import com.thesis.visualizationserverthesis.utils.PreventiveActionCounter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImpactServiceImpl implements ImpactService {
    private final CEMFemaleDaysRepository cemFemaleDaysRepository;
    private final CEMMaleDaysRepository cemMaleDaysRepository;
    private final PreventiveActionRepository preventiveActionRepository;

    @Override
    public PreventiveActionSearchResponse searchPreventiveActions(PreventiveActionsFilter filter){
        val response = new PreventiveActionSearchResponse();
        if(filter.getDistrict() != 0L){
            val aggregatedAssistants = preventiveActionRepository.getAggregatedAssistantsForTimeAndStateAndProvinceAndDistrict(filter);
            val preventiveActions = preventiveActionRepository.countPreventiveActionsForTimeAndUbigeo(filter);
            response.setAggregatedAssistants(new AggregatedAppAssistants(aggregatedAssistants));
            response.setPreventiveActions(preventiveActions.stream().map(PreventiveActionCounter::new).collect(Collectors.toList()));
        } else if(filter.getProvince() != 0L){
            val aggregatedAssistants = preventiveActionRepository.getAggregatedAssistantsForTimeAndStateAndProvince(filter);
            val preventiveActions = preventiveActionRepository.countPreventiveActionsForTimeAndProvince(filter);
            response.setAggregatedAssistants(new AggregatedAppAssistants(aggregatedAssistants));
            response.setPreventiveActions(preventiveActions.stream().map(PreventiveActionCounter::new).collect(Collectors.toList()));
        } else if(filter.getState() != 0L){
            val aggregatedAssistants = preventiveActionRepository.getAggregatedAssistantsForTimeAndState(filter);
            val preventiveActions = preventiveActionRepository.countPreventiveActionsForTimeAndState(filter);
            response.setAggregatedAssistants(new AggregatedAppAssistants(aggregatedAssistants));
            response.setPreventiveActions(preventiveActions.stream().map(PreventiveActionCounter::new).collect(Collectors.toList()));
        } else {
            val aggregatedAssistants = preventiveActionRepository.getAggregatedAssistantsForTime(filter);
            val preventiveActions = preventiveActionRepository.countPreventiveActionsForTime(filter);
            response.setAggregatedAssistants(new AggregatedAppAssistants(aggregatedAssistants));
            response.setPreventiveActions(preventiveActions.stream().map(PreventiveActionCounter::new).collect(Collectors.toList()));
        }
        return response;
    }

    private List<LocalDate> prepareDateArrayForCEMCases(ImpactViolenceCasesFilter filter){
        val dates = new ArrayList<LocalDate>();
        for(int i = 0; i < filter.getDaysBefore(); i++){
            val difference = filter.getDaysBefore()-i;
            dates.add(filter.getAppDate().minusDays(difference));
        }
        dates.add(filter.getAppDate());
        for(int i = 0; i < filter.getDaysAfter(); i++){
            val increment = 1L+i;
            dates.add(filter.getAppDate().plusDays(increment));
        }
        return dates;
    }

    @Override
    public ImpactViolenceCasesDTO getViolenceCasesForImpact(ImpactViolenceCasesFilter filter){
        val dates = prepareDateArrayForCEMCases(filter);

        val startDate = dates.get(0);
        val lastDate = dates.get(dates.size()-1);
        val maleCases = new ArrayList<CEMMaleDays>();
        val femaleCases = new ArrayList<CEMFemaleDays>();
        maleCases.addAll(cemMaleDaysRepository.searchCasesForImpact(filter,startDate,lastDate));
        femaleCases.addAll(cemFemaleDaysRepository.searchCasesForImpact(filter,startDate,lastDate));

        return new ImpactViolenceCasesDTO(dates,maleCases,femaleCases,filter.getAppDate());

    }
}
