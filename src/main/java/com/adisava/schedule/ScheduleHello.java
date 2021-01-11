package com.adisava.schedule;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class ScheduleHello {

    private AtomicInteger count = new AtomicInteger();

    int get() {
        return count.get();
    }

    @Scheduled(every = "50s")
    void fiveSeconds(ScheduledExecution execution) {
        count.incrementAndGet();
        System.out.println("Running counter: 'Fifty Seconds'. Next fire: "
                + execution.getTrigger().getNextFireTime());
    }
}
