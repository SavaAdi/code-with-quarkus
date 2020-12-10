package com.adisava;

import com.adisava.model.Developer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/developer")
public class DeveloperResource {

    /*
    POST: curl -d "{"""name""":"""Ada"""}" -H "Content-Type: application/json" -X POST http://localhost:8080/developer -v
     */

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDeveloper(Developer developer) {
        developer.persist();

        return Response.created(
                UriBuilder
                        .fromResource(DeveloperResource.class)
                        .path(Long.toString(developer.getId()))
                        .build()
                )
                .entity(developer)
                .build();
    }
}
