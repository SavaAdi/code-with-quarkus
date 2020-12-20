package com.adisava.observability;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Liveness
public class LivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder checkResponseBuilder = HealthCheckResponse
                .named("This is custom liveness"); // Health check name

        if(isUpAndRunning()) {
            return checkResponseBuilder.up().build(); // Result if up
        } else {
            return checkResponseBuilder.down()
                    .withData("reason", "Failed connection")
                    .build(); // Result if down + reason
        }

    }

    private boolean isUpAndRunning() {
        return true;
    }

}
