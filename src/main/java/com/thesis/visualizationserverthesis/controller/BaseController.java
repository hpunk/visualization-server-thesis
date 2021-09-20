package com.thesis.visualizationserverthesis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class BaseController {

    private final ObjectMapper mapper;

    protected <T> T mapQueryTo(Map<String, Object> paramsMap, Class<T> clazz){
        T params = this.mapper.convertValue(paramsMap,clazz);
        return params;
    }

    public BaseController(ObjectMapper mapper){
        this.mapper = mapper;
    }
}
