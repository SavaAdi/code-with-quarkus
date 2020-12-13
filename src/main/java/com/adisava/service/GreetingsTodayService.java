package com.adisava.service;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;

@ApplicationScoped
public class GreetingsTodayService {

    public String getGreetingToday() {
        return "Hello, today is " + LocalDate.now();
    }
}
