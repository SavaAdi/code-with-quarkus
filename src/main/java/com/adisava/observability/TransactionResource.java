package com.adisava.observability;

import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tx")
public class TransactionResource {

    @Counted(name = "number-of-transactions",
            displayName = "Transactions",
            description = "How many transactions have been processed")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doTransaction(Transaction transaction) {
        return Response.ok().build();
    }
}
