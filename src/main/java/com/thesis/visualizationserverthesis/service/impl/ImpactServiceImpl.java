package com.thesis.visualizationserverthesis.service.impl;

import com.thesis.visualizationserverthesis.model.api.*;
import com.thesis.visualizationserverthesis.repository.impact.CEMFemaleDaysRepository;
import com.thesis.visualizationserverthesis.repository.impact.CEMMaleDaysRepository;
import com.thesis.visualizationserverthesis.repository.impact.PreventiveActionRepository;
import com.thesis.visualizationserverthesis.service.ImpactService;
import com.thesis.visualizationserverthesis.utils.AggregatedAppAssistants;
import com.thesis.visualizationserverthesis.utils.CemCountImpact;
import com.thesis.visualizationserverthesis.utils.PreventiveActionCounter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
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

    private List<LocalDate> prepareDateArrayForCEMCases(ImpactViolenceCasesFilter filter) throws ParseException {
        val dates = new ArrayList<LocalDate>();
        for(int i = 0; i < filter.getDaysBefore(); i++){
            val difference = filter.getDaysBefore()-i;
            dates.add(filter.getAppDateStart().minusDays(difference));
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date firstDate = sdf.parse(filter.getAppDateStart().toString());
        Date secondDate = sdf.parse(filter.getAppDateEnd().toString());

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1l;

        for(int i = 0; i<Long.valueOf(diff).intValue(); i++){
            val increment =  i;
            dates.add(filter.getAppDateStart().plusDays(increment));
        }
        for(int i = 0; i < filter.getDaysAfter(); i++){
            val increment = 1L+i;
            dates.add(filter.getAppDateEnd().plusDays(increment));
        }
        return dates;
    }

    @Override
    public ImpactViolenceCasesDTO getViolenceCasesForImpact(ImpactViolenceCasesFilter filter) throws ParseException {
        val dates = prepareDateArrayForCEMCases(filter);

        val startDate = dates.get(0);
        val lastDate = dates.get(dates.size()-1);
        val maleCases = new ArrayList<CemCountImpact>();
        val femaleCases = new ArrayList<CemCountImpact>();
        if(filter.getDistrict() != 0L){
            maleCases.addAll(cemMaleDaysRepository.searchCasesForImpactDistrict(filter,startDate,lastDate).stream().map(CemCountImpact::new).collect(Collectors.toList()));
            femaleCases.addAll(cemFemaleDaysRepository.searchCasesForImpactDistrict(filter,startDate,lastDate).stream().map(CemCountImpact::new).collect(Collectors.toList()));
        } else if(filter.getProvince() != 0L){
            maleCases.addAll(cemMaleDaysRepository.searchCasesForImpactProvince(filter,startDate,lastDate).stream().map(CemCountImpact::new).collect(Collectors.toList()));
            femaleCases.addAll(cemFemaleDaysRepository.searchCasesForImpactProvince(filter,startDate,lastDate).stream().map(CemCountImpact::new).collect(Collectors.toList()));
        } else if(filter.getState() != 0L) {
            maleCases.addAll(cemMaleDaysRepository.searchCasesForImpactState(filter,startDate,lastDate).stream().map(CemCountImpact::new).collect(Collectors.toList()));
            femaleCases.addAll(cemFemaleDaysRepository.searchCasesForImpactState(filter,startDate,lastDate).stream().map(CemCountImpact::new).collect(Collectors.toList()));
        } else {
            maleCases.addAll(cemMaleDaysRepository.searchCasesForImpact(startDate,lastDate).stream().map(CemCountImpact::new).collect(Collectors.toList()));
            femaleCases.addAll(cemFemaleDaysRepository.searchCasesForImpact(startDate,lastDate).stream().map(CemCountImpact::new).collect(Collectors.toList()));
        }
        return new ImpactViolenceCasesDTO(dates,maleCases,femaleCases,filter.getAppDateStart());

    }

    @Override
    public List<AppPerDay> getAppPerDay(PreventiveActionsFilter filter){
        List<AppPerDay> response = new ArrayList<>();
        PreventiveActionsFilter auxFilter = new PreventiveActionsFilter();
        auxFilter.setProvince(filter.getProvince());
        auxFilter.setDistrict(filter.getDistrict());
        auxFilter.setState(filter.getState());
        for(LocalDate auxDate = filter.getStartDate(); auxDate.isBefore(filter.getEndDate().plusDays(1l)) ; auxDate = auxDate.plusDays(1l)){
            AppPerDay appPerDay = new AppPerDay();

            appPerDay.setDate(auxDate);

            auxFilter.setStartDate(auxDate);
            auxFilter.setEndDate(auxDate);
            if(filter.getDistrict() != 0L){
                appPerDay.setPreventiveActions(preventiveActionRepository.countPreventiveActionsForTimeAndUbigeo(auxFilter).stream().map(PreventiveActionCounter::new).collect(Collectors.toList()));
                appPerDay.setCount(new PreventiveActionCounter(preventiveActionRepository.getTotalPreventiveActionsForTimeAndUbigeo(auxFilter)).getCount());
            } else if(filter.getProvince() != 0L){
                appPerDay.setPreventiveActions(preventiveActionRepository.countPreventiveActionsForTimeAndProvince(auxFilter).stream().map(PreventiveActionCounter::new).collect(Collectors.toList()));
                appPerDay.setCount(new PreventiveActionCounter(preventiveActionRepository.getTotalPreventiveActionsForTimeAndProvince(auxFilter)).getCount());
            } else if(filter.getState() != 0L){
                appPerDay.setPreventiveActions(preventiveActionRepository.countPreventiveActionsForTimeAndState(auxFilter).stream().map(PreventiveActionCounter::new).collect(Collectors.toList()));
                appPerDay.setCount(new PreventiveActionCounter(preventiveActionRepository.getTotalPreventiveActionsForTimeAndState(auxFilter)).getCount());
            } else {
                appPerDay.setPreventiveActions(preventiveActionRepository.countPreventiveActionsForTime(auxFilter).stream().map(PreventiveActionCounter::new).collect(Collectors.toList()));
                appPerDay.setCount(new PreventiveActionCounter(preventiveActionRepository.getTotalPreventiveActionsForTime(auxFilter)).getCount());
            }
            response.add(appPerDay);
        }

        return response;
    }
}
