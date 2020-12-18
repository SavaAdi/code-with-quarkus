package com.adisava.panache;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collections;
import java.util.List;

@Path("/library")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class LibraryService {

    @Inject
    LibraryRepository libraryRepository;

    // tag::persist[]
    @POST
    public Response newLibrary(Library library) {
        library.persist();
        return Response.created(URI.create("/library/" + library.encodedName()))
                .entity(library).build();
    }
    // end::persist[]

    @GET
    public List<Library> getAllLibraries() {
        return libraryRepository.findAll().list();
    }

    @GET
    @Path("/{name}")
    public Library getLibraryByName(@PathParam("name") String name) {
        return libraryRepository.findByName(name);
    }



    @GET
    @Path("/new")
    public Response getALibrary() {
        return null;
    }
}

