package com.thesis.visualizationserverthesis.service.impl;

import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesDTO;
import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesFilter;
import com.thesis.visualizationserverthesis.model.api.PreventiveActionsFilter;
import com.thesis.visualizationserverthesis.model.entity.base.PreventiveAction;
import com.thesis.visualizationserverthesis.model.entity.impact.*;
import com.thesis.visualizationserverthesis.repository.impact.*;
import com.thesis.visualizationserverthesis.service.ImpactService;
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

    private final PreventiveActionEighteenRepository preventiveActionEighteenRepository;
    private final PreventiveActionNineteenRepository preventiveActionNineteenRepository;
    private final PreventiveActionSeventeenRepository preventiveActionSeventeenRepository;
    private final PreventiveActionTwentyRepository preventiveActionTwentyRepository;

    @Override
    public List<PreventiveAction> searchPreventiveActions(PreventiveActionsFilter filter){
        val response = new ArrayList<PreventiveAction>();
        if(filter.getStartDate().getYear()<=2020 && filter.getEndDate().getYear()>=2020){
            val listTwenty = preventiveActionTwentyRepository.searchPreventiveActions(filter)
                    .stream().map(PreventiveActionTwenty::toPreventiveAction).collect(Collectors.toList());
            response.addAll(listTwenty);
        }
        if(filter.getStartDate().getYear()<=2019 && filter.getEndDate().getYear()>=2019){
            val listNineteen = preventiveActionNineteenRepository.searchPreventiveActions(filter)
                    .stream().map(PreventiveActionNineteen::toPreventiveAction).collect(Collectors.toList());
            response.addAll(listNineteen);
        }
        if(filter.getStartDate().getYear()<=2018 && filter.getEndDate().getYear()>=2018){
            val listEighteen = preventiveActionEighteenRepository.searchPreventiveActions(filter)
                    .stream().map(PreventiveActionEighteen::toPreventiveAction).collect(Collectors.toList());
            response.addAll(listEighteen);
        }
        if(filter.getStartDate().getYear()==2017){
            val listSeventeen = preventiveActionSeventeenRepository.searchPreventiveActions(filter)
                    .stream().map(PreventiveActionSeventeen::toPreventiveAction).collect(Collectors.toList());
            response.addAll(listSeventeen);
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
