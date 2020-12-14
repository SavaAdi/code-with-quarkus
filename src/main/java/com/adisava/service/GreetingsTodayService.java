package com.adisava.service;

import com.adisava.interceptors.PerformanceLogEvent;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;

@ApplicationScoped
public class GreetingsTodayService {

    @PerformanceLogEvent
    public String getGreetingToday() throws InterruptedException {
        Thread.sleep(1000);
        return "Hello, today is " + LocalDate.now();
    }
}
