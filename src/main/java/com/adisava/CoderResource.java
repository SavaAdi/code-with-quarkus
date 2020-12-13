package com.adisava;

import com.adisava.model.Coder;
import com.adisava.model.Developer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/coder")
public class CoderResource {

    private static final List<Coder> coders = new ArrayList<>();

    /*
    Command: curl -d '{"""name""":"""Alex""","""age""":39, """favoriteLanguage""":"""java"""}' -H "Content-Type: application/json" -X POST http://localhost:8080/coder
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDeveloper(Coder coder) {
        coders.add(coder);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Coder> getDevelopers() {
        return coders;
    }
}
