package com.thesis.visualizationserverthesis.service.impl;

import com.thesis.visualizationserverthesis.model.api.*;
import com.thesis.visualizationserverthesis.model.entity.WeekDate;
import com.thesis.visualizationserverthesis.repository.WeekDateRepository;
import com.thesis.visualizationserverthesis.repository.evolution.CEMFemaleWeeksRepository;
import com.thesis.visualizationserverthesis.repository.evolution.CEMMaleWeeksRepository;
import com.thesis.visualizationserverthesis.repository.impact.CEMFemaleDaysRepository;
import com.thesis.visualizationserverthesis.repository.impact.CEMMaleDaysRepository;
import com.thesis.visualizationserverthesis.service.EvolutionService;
import com.thesis.visualizationserverthesis.utils.ICEMWeeks;
import com.thesis.visualizationserverthesis.utils.IUbigeoCountProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EvolutionServiceImpl implements EvolutionService {
    private final CEMFemaleWeeksRepository cemFemaleWeeksRepository;
    private final CEMMaleWeeksRepository cemMaleWeeksRepository;

    private final CEMFemaleDaysRepository cemFemaleDaysRepository;
    private final CEMMaleDaysRepository cemMaleDaysRepository;

    private final WeekDateRepository weekDateRepository;

    @Override
    public List<CasesByUbigeoForMonth> getCasesByUbigeoForMonth(EvolutionCasesByMonthFilter filter){
        // por cada mes y año debo hacer una agregación por departamento o provincias de un departamento
        LocalDate tempDate = filter.getStartDate().withDayOfMonth(1);
        List<CasesByUbigeoForMonth> response = new ArrayList<>();
        do {
            //agregar por departamento o provincia
            CasesByUbigeoForMonth item = new CasesByUbigeoForMonth();
            item.setMonth(tempDate.getMonthValue());
            item.setYear(tempDate.getYear());
            List<CaseByUbigeo> casesByUbigeo = new ArrayList<>();
            //Query para traer data filtrada y agregada por mes - año - provincia o mes-año-departamento
            LocalDate firstDayMonth = LocalDate.of(item.getYear(), item.getMonth(), 1);
            LocalDate firstDayNextMonth = firstDayMonth.plusMonths(1L);
            if (filter.getFilterBy().equals("STATE")) {
                val femaleList = cemFemaleDaysRepository.aggregateByState(firstDayMonth, firstDayNextMonth);
                val maleList = cemMaleDaysRepository.aggregateByState(firstDayMonth, firstDayNextMonth);
                casesByUbigeo.addAll(generateCasesByUbigeo(femaleList, maleList));

            } else if (filter.getFilterBy().equals("PROVINCE")) {
                val femaleList = cemFemaleDaysRepository.aggregateByProvince(firstDayMonth, firstDayNextMonth, filter.getState());
                val maleList = cemMaleDaysRepository.aggregateByProvince(firstDayMonth, firstDayNextMonth, filter.getState());
                casesByUbigeo.addAll(generateCasesByUbigeo(femaleList, maleList));
            }
            item.setCasesByUbigeo(casesByUbigeo);
            tempDate = tempDate.plusMonths(1);
            log.info("el tempDate {}, el endDate {}", tempDate, filter.getEndDate());
            response.add(item);
        } while((tempDate.getYear()*100 + tempDate.getMonth().getValue()) <= (filter.getEndDate().getYear()*100 + filter.getEndDate().getMonth().getValue()) );
        //} while(tempDate.getMonth().getValue() <= filter.getEndDate().getMonth().getValue() || tempDate.getYear() <= filter.getEndDate().getYear());
        return response;
    }

    private List<CaseByUbigeo> generateCasesByUbigeo(List<IUbigeoCountProjection> femaleList, List<IUbigeoCountProjection> maleList ){
        Integer maleIndex = 0;
        Integer femaleIndex = 0;
        List<CaseByUbigeo> response = new ArrayList<>();
        //se arma el arreglo con ubigeos
        while(maleIndex!=maleList.size() || femaleIndex!=femaleList.size()){
            CaseByUbigeo item = new CaseByUbigeo();
            if(maleIndex != maleList.size() && femaleIndex!=femaleList.size()) {
                val maleUbigeo = maleList.get(maleIndex).getUbigeo();
                val femaleUbigeo = femaleList.get(femaleIndex).getUbigeo();
                if(maleUbigeo.equals(femaleUbigeo)){
                    item.setUbigeo(femaleList.get(femaleIndex).getUbigeo());
                    maleIndex++;
                    femaleIndex++;
                } else {
                    if(maleUbigeo < femaleUbigeo){
                        item.setUbigeo(maleUbigeo);
                        maleIndex++;
                    } else {
                        item.setUbigeo(femaleUbigeo);
                        femaleIndex++;
                    }
                }
            }
            else if(maleIndex != maleList.size()) {
                item.setUbigeo(maleList.get(maleIndex).getUbigeo());
                maleIndex++;
            }
            else if(femaleIndex != femaleList.size()){
                item.setUbigeo(femaleList.get(femaleIndex).getUbigeo());
                femaleIndex++;
            }
            response.add(item);
        }

        //se llena el numero de casos del arreglo con ubigeos
        maleIndex = 0;
        femaleIndex = 0;

        for(int i=0; i< response.size(); i++){
            if(femaleIndex != femaleList.size() && femaleList.get(femaleIndex).getUbigeo().equals(response.get(i).getUbigeo())){
                response.get(i).setCasesFemale(femaleList.get(femaleIndex).getCount());
                femaleIndex++;
            } else {
                response.get(i).setCasesFemale(0L);
            }
            if(maleIndex != maleList.size() && maleList.get(maleIndex).getUbigeo().equals(response.get(i).getUbigeo())){
                response.get(i).setCasesMale(maleList.get(maleIndex).getCount());
                maleIndex ++;
            } else {
                response.get(i).setCasesMale(0L);
            }
        }

        return response;
    }

    @Override
    public List<DetailedCasesByWeek> getDetailedCasesByWeek(EvolutionDetailedCasesFilter filter){
        Long initialWeek = weekDateRepository.getWeekFromDate(filter.getStartDate());
        Long finalWeek = weekDateRepository.getWeekFromDate(filter.getEndDate());

        List<DetailedCasesByWeek> response = new ArrayList<>();

        for(Long week = initialWeek; week<=finalWeek ; week++){
            //obtenemos las fechas por semana para el objeto,
            WeekDate weekEntity = weekDateRepository.findById(week).get();
            //agregamos casos por semana para un estado o una provincia
            if(filter.getFilterBy().equals("PROVINCE")) {
                ICEMWeeks maleCases = cemMaleWeeksRepository.getAggregatedForWeekAndProvince(week,filter).orElse(null);
                ICEMWeeks femaleCases = cemFemaleWeeksRepository.getAggregatedForWeekAndProvince(week,filter).orElse(null);
                response.add(new DetailedCasesByWeek(weekEntity,maleCases,femaleCases));
            }
            else if(filter.getFilterBy().equals("STATE")) {
                ICEMWeeks maleCases = cemMaleWeeksRepository.getAggregatedForWeekAndState(week,filter).orElse(null);
                ICEMWeeks femaleCases = cemFemaleWeeksRepository.getAggregatedForWeekAndState(week,filter).orElse(null);
                response.add(new DetailedCasesByWeek(weekEntity,maleCases,femaleCases));
            }
            //Adicionamos al response
        }

        return response;
    }
}
