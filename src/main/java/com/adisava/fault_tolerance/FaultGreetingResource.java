package com.adisava.fault_tolerance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fault")
public class FaultGreetingResource {

    @Inject
    ServiceInvoker serviceInvoker;

    @GET
    @Path("/retry")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloRetryFallback() {
        return serviceInvoker.getHelloWithFallback();
    }

}
