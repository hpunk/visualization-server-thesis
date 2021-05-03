package com.thesis.visualizationserverthesis.model.api;

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
}
