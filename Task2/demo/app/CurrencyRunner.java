package com.example.demo.app;

import  java.util.*;
import  com.example.demo.model.CurrencyResponse;
import  com.example.demo.service.CurrencyService;
import  com.fasterxml.jackson.databind.ObjectMapper;
import  org.springframework.boot.CommandLineRunner;
import  org.springframework.stereotype.Component;

@Component
public class CurrencyRunner implements  CommandLineRunner
{
    private final CurrencyService service;
    private final ObjectMapper mapper = new ObjectMapper();
    
    public CurrencyRunner(CurrencyService service)
    {
        this.service = service;
    }

    @Override
    public void run(String...args) throws  Exception
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Base Currency(USD,INR,EUR):");
        String base = scanner.nextLine().toUpperCase();

        System.out.println("Enter target Currency:");
        String target = scanner.nextLine().toUpperCase();
        
        System.out.println("Enter Amount");
        double amount = scanner.nextDouble();

        String json = service.getRates(base);

        CurrencyResponse response = mapper.readValue(json,CurrencyResponse.class);

        double rate = response.getRates().get(target);

        double converted = amount * rate;

        System.out.println("\nConverted Amount:"+converted +" " +target);
    }

}
