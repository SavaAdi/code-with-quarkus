package com.adisava.service;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;

@ApplicationScoped
public class GreetingsTodayService {

    public String getGreetingToday() throws InterruptedException {
        Thread.sleep(1000);
        return "Hello, today is " + LocalDate.now();
    }
}
