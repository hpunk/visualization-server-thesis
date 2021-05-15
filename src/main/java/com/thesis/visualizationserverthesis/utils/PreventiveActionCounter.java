package com.thesis.visualizationserverthesis.utils;

import lombok.Data;

@Data
public class PreventiveActionCounter {
    private Integer count;
    private String actionType;
    private Long state;
    private Long province;
    private Long district;

    public PreventiveActionCounter(IPreventiveActionCounterProjection projection){
        this.count = projection.getCount();
        this.actionType = projection.getActionType();
        this.state = projection.getState();
        this.province = projection.getProvince();
        this.district = projection.getDistrict();
    }
}
