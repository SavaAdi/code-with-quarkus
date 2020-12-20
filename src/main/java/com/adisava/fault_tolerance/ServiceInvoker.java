package com.adisava.fault_tolerance;

import org.eclipse.microprofile.faulttolerance.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ServiceInvoker {

    @Inject
    FailureSimulator failureSimulator;

    @Retry(maxRetries = 3, delay = 1000)
    @Fallback(RecoverHelloMessageFallback.class)
    public String getHelloWithFallback() {
        failureSimulator.failAlways();
        return "It worked";
    }

    public static class RecoverHelloMessageFallback implements FallbackHandler<String> //Same type as the return type of the fallbackMethod
    {
        @Override
        public String handle(ExecutionContext executionContext) {
            return "I recovered";
        }
    }

    @Timeout(value = 2000) // Timeouts if the return takes longer than 2 secs
    public String getHelloWithTimeout() {
        failureSimulator.longMethod();
        return "hello";
    }


}
