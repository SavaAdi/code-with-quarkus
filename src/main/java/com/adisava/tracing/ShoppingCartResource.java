package com.adisava.tracing;

import io.opentracing.Tracer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/shop")
public class ShoppingCartResource {

    @Inject
    Tracer tracer;

    @POST
    @Path("/add/{customerId}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(@PathParam("customerId") String customerId, Item item) {

        if (customerId.startsWith("1")) {
            tracer.activeSpan().setTag("important.customer", true);
        }

        Optional<Cart> cart = Optional.ofNullable(Cart.find("customerId", customerId).firstResult());

        Cart currentCart = cart.orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.customerId = customerId;
            return newCart;
        });

        currentCart.addItem(item);
        currentCart.persist();
        return Response.ok().build();

    }

}

