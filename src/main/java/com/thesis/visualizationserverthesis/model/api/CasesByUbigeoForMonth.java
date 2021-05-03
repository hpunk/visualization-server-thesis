package com.thesis.visualizationserverthesis.model.api;

import lombok.Data;

import java.util.List;

@Data
public class CasesByUbigeoForMonth {
    private Integer year;
    private Integer month;
    private List<CaseByUbigeo> casesByUbigeo;
}
