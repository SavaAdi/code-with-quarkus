package com.adisava.service;


import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Traced
public class SalutationsServiceImpl implements SalutationsService{

    public String message() {
        return "I salute the coder!";
    }

    public String message(String coderName) {
        return "I salute, " + coderName +  " the coder!";
    }
}
