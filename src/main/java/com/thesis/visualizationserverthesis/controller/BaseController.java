package com.thesis.visualizationserverthesis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class BaseController {

    @Autowired
    private ObjectMapper mapper;

    protected <T> T mapQueryTo(Map<String, Object> paramsMap, Class<T> clazz){
        T params = this.mapper.convertValue(paramsMap,clazz);
        return params;
    }
}
