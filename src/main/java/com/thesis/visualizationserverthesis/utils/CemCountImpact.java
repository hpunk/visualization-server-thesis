package com.thesis.visualizationserverthesis.utils;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CemCountImpact {
    private Long stateCode;
    private Long provinceCode;
    private Long districtCode;
    private LocalDate caseDate;
    private Long physicalVCount;
    private Long sexualVCount;
    private Long economicalVCount;
    private Long psychologicalVCount;

    public CemCountImpact(ICemCountImpact projection){
        this.stateCode = projection.getStateCode();
        this.provinceCode = projection.getProvinceCode();
        this.districtCode = projection.getDistrictCode();
        this.caseDate = projection.getCaseDate();
        this.physicalVCount = projection.getPhysicalVCount();
        this.sexualVCount = projection.getSexualVCount();
        this.economicalVCount = projection.getEconomicalVCount();
        this.psychologicalVCount = projection.getPsychologicalVCount();
    }
}