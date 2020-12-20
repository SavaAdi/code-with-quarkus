package com.adisava.fault_tolerance;

import org.eclipse.microprofile.faulttolerance.exceptions.TimeoutException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    @GET
    @Path("/timeout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response helloTimeout() {
        try {
            return Response.ok(serviceInvoker.getHelloWithTimeout()).build();
        } catch(TimeoutException e) {
            return Response.serverError().entity("Timeout").build();
        }
    }

    @GET
    @Path("/bulkhead")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloBulkhead() {
        return serviceInvoker.getHelloBulkhead();
    }

}
