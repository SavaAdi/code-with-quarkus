package com.adisava.fault_tolerance;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class FailureSimulator {

    private int consecutiveErrors = 0;

    public void failAlways() {
        throw new IllegalStateException();
    }

    public void longMethod() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
    }

    public void fail4Consecutive() {
        if (consecutiveErrors < 4) {
            consecutiveErrors++;
            throw new IllegalStateException();
        }
        consecutiveErrors = 0;
    }

    public void shortMethod() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
        }
    }

}
