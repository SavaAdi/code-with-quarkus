package com.adisava;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class GreetingMain implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        System.out.println("Hello World - I am alive");
        Quarkus.waitForExit();
        return 0;
    }
}
