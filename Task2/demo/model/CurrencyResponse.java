package com.example.demo.model;

import  java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponse 
{
    private String base;
    private Map<String,Double> rates;

    public String getBase()
    {
        return base;
    }

    public Map<String,Double> getRates()
    {   
        return rates;
    }
}
