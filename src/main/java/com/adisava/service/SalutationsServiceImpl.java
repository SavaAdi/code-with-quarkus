package com.adisava.service;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SalutationsServiceImpl implements SalutationsService{

    public String message() {
        return "I salute the coder!";
    }
}
