package com.adisava;

import com.adisava.model.Quark;
import com.adisava.rest.LOCK;
import com.adisava.resteasyjackson.JacksonResource;

import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/hello-resteasy")
public class GreetingResource {

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
    @Produces(MediaType.TEXT_PLAIN)
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


     */

    @LOCK
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String lockResource(@PathParam("id") long id) {
        return id + " locked";
    }
}