package com.thesis.visualizationserverthesis.utils;

import java.time.LocalDate;

public interface ICemCountImpact {
    Long getStateCode();
    Long getProvinceCode();
    Long getDistrictCode();
    LocalDate getCaseDate();
    Long getPhysicalVCount();
    Long getSexualVCount();
    Long getEconomicalVCount();
    Long getPsychologicalVCount();
}
