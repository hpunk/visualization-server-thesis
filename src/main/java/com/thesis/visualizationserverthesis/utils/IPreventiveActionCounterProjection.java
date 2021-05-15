package com.thesis.visualizationserverthesis.utils;

public interface IPreventiveActionCounterProjection {
    Integer getCount();
    String getActionType();
    Long getState();
    Long getProvince();
    Long getDistrict();
}
