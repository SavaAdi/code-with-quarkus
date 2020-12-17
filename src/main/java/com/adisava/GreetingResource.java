package com.adisava;

import com.adisava.model.Quark;
import com.adisava.rest.LOCK;
import com.adisava.service.GreetingsTodayService;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import io.quarkus.runtime.annotations.CommandLineArguments;
import org.jboss.resteasy.annotations.cache.Cache;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/hello-resteasy")
public class GreetingResource {

    @Inject
    GreetingsTodayService greetingsTodayService;

    @CommandLineArguments
    String[] args;

    public enum Order {
        desc, asc;
    }

    @GET
    @Path("/old")
    @Produces(MediaType.APPLICATION_JSON)
    public Quark hello() {
        return new Quark("hello", "I AM YOUR DOOM");
    }

    /*
    Good request: curl -X GET "http://localhost:8080/hello-resteasy?order=asc" -H "accept: text/plain" -H "authorization: XYZ"
    Bad request: curl -X GET "http://localhost:8080/hello-resteasy?order=asc" -H "accept: text/plain" -v
                * Bad Request is still 200 but the authorization is null
     */

    @GET
    @Produces(TEXT_PLAIN)
    public String hello(
            @Context UriInfo uriInfo,
            @QueryParam("order") Order order,
            @NotBlank @HeaderParam("authorization") String authorization
    ) {

        return String.format("URI: %s - Order %s - Authorization: %s",
                uriInfo.getAbsolutePath(), order, authorization);
    }

    /*
    Extending supported HTTP verbs using HttpMethod and custom annotations
    Command:  curl -X LOCK http://localhost:8080/hello-resteasy/1
     */

    @LOCK
    @Produces(TEXT_PLAIN)
    @Path("{id}")
    public String lockResource(@PathParam("id") long id) {
        return id + " locked";
    }

    @GET
    @Cache(maxAge = 3000) //Only adds Cache-Control headers
    @CacheResult(cacheName = "service")
    @Path("/service")
    @Produces(TEXT_PLAIN)
    public String helloToday() throws InterruptedException {
        return greetingsTodayService.getGreetingToday();
    }

    @GET
    @Path("/service/invalidate")
    @Produces(TEXT_PLAIN)
    @CacheInvalidateAll(cacheName = "service") //invalidates for all keys...not that we have any in this case
    public String invalidateCache() {
        return "Cache was invalidated";
    }

    @GET
    @Path("/argument")
    @Produces(TEXT_PLAIN)
    public String getFirstArgument() {
        return args[0];
    }
}