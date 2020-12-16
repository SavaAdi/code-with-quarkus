package com.adisava.common;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Collections;
import java.util.Map;

public class HelloWorldQuarkusTestResourceLifecycleManager implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        System.out.println("Start Test Suite execution");
        return Collections.emptyMap();
    }

    @Override
    public void stop() {
        System.out.println("Stop Test Suite execution");
    }

    @Override
    public void inject(Object testInstance) {
        System.out.println("Executing " + testInstance.getClass().getName());
    }

    @Override
    public int order() {
        return 0;
    }
}
