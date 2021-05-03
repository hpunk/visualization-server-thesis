package com.thesis.visualizationserverthesis.model.api;

import com.thesis.visualizationserverthesis.model.entity.WeekDate;
import com.thesis.visualizationserverthesis.utils.ICEMWeeks;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DetailedCasesByWeek {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long manPhysical;
    private Long manEconomical;
    private Long manSexual;
    private Long manPsychological;
    private Long manFirstTime;
    private Long manAggrAlcohol;
    private Long manAggrDrug;
    private Long manVictAlcohol;
    private Long manVictDrug;
    private Long manVictLgtbi;
    private Long manVictDisab;
    private Long manInf;
    private Long manNin;
    private Long manAdol;
    private Long manAdolT;
    private Long manJov;
    private Long manAdul;
    private Long manMayo;
    private Long manFamily;
    private Long manLove;
    private Long manNoRelation;

    private Long womanPhysical;
    private Long womanEconomical;
    private Long womanSexual;
    private Long womanPsychological;
    private Long womanFirstTime;
    private Long womanAggrAlcohol;
    private Long womanAggrDrug;
    private Long womanVictAlcohol;
    private Long womanVictDrug;
    private Long womanVictLgtbi;
    private Long womanVictDisab;
    private Long womanInf;
    private Long womanNin;
    private Long womanAdol;
    private Long womanAdolT;
    private Long womanJov;
    private Long womanAdul;
    private Long womanMayo;
    private Long womanFamily;
    private Long womanLove;
    private Long womanNoRelation;

    public DetailedCasesByWeek(WeekDate week, ICEMWeeks maleCases, ICEMWeeks femaleCases){
        this.endDate = week.getEndDate();
        this.startDate = week.getStartDate();
        if(maleCases == null){
            this.manPhysical = 0L;
            this.manEconomical = 0L;
            this.manSexual = 0L;
            this.manPsychological = 0L;
            this.manFirstTime = 0L;
            this.manAggrAlcohol = 0L;
            this.manAggrDrug = 0L;
            this.manVictAlcohol = 0L;
            this.manVictDrug = 0L;
            this.manVictLgtbi = 0L;
            this.manVictDisab = 0L;
            this.manInf = 0L;
            this.manNin = 0L;
            this.manAdol = 0L;
            this.manAdolT = 0L;
            this.manJov = 0L;
            this.manAdul = 0L;
            this.manMayo = 0L;
            this.manFamily = 0L;
            this.manLove = 0L;
            this.manNoRelation = 0L;
        }else {
            this.manPhysical = maleCases.getPhysical();
            this.manEconomical = maleCases.getEconomical();
            this.manSexual = maleCases.getSexual();
            this.manPsychological = maleCases.getPsychological();
            this.manFirstTime = maleCases.getFirstTime();
            this.manAggrAlcohol = maleCases.getAggrAlcohol();
            this.manAggrDrug = maleCases.getAggrDrug();
            this.manVictAlcohol = maleCases.getVictAlcohol();
            this.manVictDrug = maleCases.getVictDrug();
            this.manVictLgtbi = maleCases.getVictLgtbi();
            this.manVictDisab = maleCases.getVictDisab();
            this.manInf = maleCases.getInf();
            this.manNin = maleCases.getNin();
            this.manAdol = maleCases.getAdol();
            this.manAdolT = maleCases.getAdolT();
            this.manJov = maleCases.getJov();
            this.manAdul = maleCases.getAdul();
            this.manMayo = maleCases.getMayo();
            this.manFamily = maleCases.getFamily();
            this.manLove = maleCases.getLove();
            this.manNoRelation = maleCases.getNoRelation();
        }

        if(femaleCases == null){
            this.womanPhysical = 0L;
            this.womanEconomical = 0L;
            this.womanSexual = 0L;
            this.womanPsychological = 0L;
            this.womanFirstTime = 0L;
            this.womanAggrAlcohol = 0L;
            this.womanAggrDrug = 0L;
            this.womanVictAlcohol = 0L;
            this.womanVictDrug = 0L;
            this.womanVictLgtbi = 0L;
            this.womanVictDisab = 0L;
            this.womanInf = 0L;
            this.womanNin = 0L;
            this.womanAdol = 0L;
            this.womanAdolT = 0L;
            this.womanJov = 0L;
            this.womanAdul = 0L;
            this.womanMayo = 0L;
            this.womanFamily = 0L;
            this.womanLove = 0L;
            this.womanNoRelation = 0L;
        } else {
            this.womanPhysical = femaleCases.getPhysical();
            this.womanEconomical = femaleCases.getEconomical();
            this.womanSexual = femaleCases.getSexual();
            this.womanPsychological = femaleCases.getPsychological();
            this.womanFirstTime = femaleCases.getFirstTime();
            this.womanAggrAlcohol = femaleCases.getAggrAlcohol();
            this.womanAggrDrug = femaleCases.getAggrDrug();
            this.womanVictAlcohol = femaleCases.getVictAlcohol();
            this.womanVictDrug = femaleCases.getVictDrug();
            this.womanVictLgtbi = femaleCases.getVictLgtbi();
            this.womanVictDisab = femaleCases.getVictDisab();
            this.womanInf = femaleCases.getInf();
            this.womanNin = femaleCases.getNin();
            this.womanAdol = femaleCases.getAdol();
            this.womanAdolT = femaleCases.getAdolT();
            this.womanJov = femaleCases.getJov();
            this.womanAdul = femaleCases.getAdul();
            this.womanMayo = femaleCases.getMayo();
            this.womanFamily = femaleCases.getFamily();
            this.womanLove = femaleCases.getLove();
            this.womanNoRelation = femaleCases.getNoRelation();
        }

    }
}
