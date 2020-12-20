package com.adisava.observability;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;

@ApplicationScoped
public class CustomHealthCheck {

    @Produces
    @Readiness
    public HealthCheck ready() {
        if (isReady()) {
            return io.smallrye.health.HealthStatus.up("This is custom readiness");
        } else {
            return io.smallrye.health.HealthStatus.down("This is custom readiness");
        }
    }

    private boolean isReady() {
        return true;
    }

}
