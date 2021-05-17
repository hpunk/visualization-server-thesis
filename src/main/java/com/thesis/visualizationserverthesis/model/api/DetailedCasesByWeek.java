package com.thesis.visualizationserverthesis.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thesis.visualizationserverthesis.model.entity.WeekDate;
import com.thesis.visualizationserverthesis.utils.ICEMWeeks;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DetailedCasesByWeek {
    private LocalDate startDate;
    private LocalDate endDate;

    @JsonProperty("man_physical_violence")
    private Long manPhysical;
    @JsonProperty("man_economical_violence")
    private Long manEconomical;
    @JsonProperty("man_sexual_violence")
    private Long manSexual;
    @JsonProperty("man_psychological_violence")
    private Long manPsychological;
    @JsonProperty("man_first_time")
    private Long manFirstTime;
    @JsonProperty("man_aggr_alcohol")
    private Long manAggrAlcohol;
    @JsonProperty("man_aggr_drugs")
    private Long manAggrDrug;
    @JsonProperty("man_vict_alcohol")
    private Long manVictAlcohol;
    @JsonProperty("man_vict_drugs")
    private Long manVictDrug;
    @JsonProperty("man_vict_lgtbi")
    private Long manVictLgtbi;
    @JsonProperty("man_vict_disability")
    private Long manVictDisab;
    @JsonProperty("man_inf")
    private Long manInf;
    @JsonProperty("man_nin")
    private Long manNin;
    @JsonProperty("man_adol")
    private Long manAdol;
    @JsonProperty("man_adol_t")
    private Long manAdolT;
    @JsonProperty("man_jov")
    private Long manJov;
    @JsonProperty("man_adul")
    private Long manAdul;
    @JsonProperty("man_mayo")
    private Long manMayo;
    @JsonProperty("man_family")
    private Long manFamily;
    @JsonProperty("man_love")
    private Long manLove;
    @JsonProperty("man_no_relation")
    private Long manNoRelation;

    @JsonProperty("woman_physical_violence")
    private Long womanPhysical;
    @JsonProperty("woman_economical_violence")
    private Long womanEconomical;
    @JsonProperty("woman_sexual_violence")
    private Long womanSexual;
    @JsonProperty("woman_psychological_violence")
    private Long womanPsychological;
    @JsonProperty("woman_first_time")
    private Long womanFirstTime;
    @JsonProperty("woman_aggr_alcohol")
    private Long womanAggrAlcohol;
    @JsonProperty("woman_aggr_drugs")
    private Long womanAggrDrug;
    @JsonProperty("woman_vict_alcohol")
    private Long womanVictAlcohol;
    @JsonProperty("woman_vict_drugs")
    private Long womanVictDrug;
    @JsonProperty("woman_vict_lgtbi")
    private Long womanVictLgtbi;
    @JsonProperty("woman_vict_disability")
    private Long womanVictDisab;
    @JsonProperty("woman_inf")
    private Long womanInf;
    @JsonProperty("woman_nin")
    private Long womanNin;
    @JsonProperty("woman_adol")
    private Long womanAdol;
    @JsonProperty("woman_adol_t")
    private Long womanAdolT;
    @JsonProperty("woman_jov")
    private Long womanJov;
    @JsonProperty("woman_adul")
    private Long womanAdul;
    @JsonProperty("woman_mayo")
    private Long womanMayo;
    @JsonProperty("woman_family")
    private Long womanFamily;
    @JsonProperty("woman_love")
    private Long womanLove;
    @JsonProperty("woman_no_relation")
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
