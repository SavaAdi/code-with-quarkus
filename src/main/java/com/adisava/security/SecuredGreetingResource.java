package com.adisava.security;

import io.quarkus.security.Authenticated;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

// Security annotations are not restricted only to JAX-RS resources. They can be used in CDI beans to protect method calls, too.
@Path("/sec-hello")
public class SecuredGreetingResource {

    @GET
    @Path("/secured")
    @RolesAllowed("Tester")
    public String greetingSecured(@Context SecurityContext sec) {
        Principal user = sec.getUserPrincipal();
        return user != null ? user.getName() + " just logged in!" : "anonymous";
    }

    @GET
    @Path("/unsecured")
    @PermitAll
    public String greetingUnsecured() { return "Free for all"; }

    @GET
    @Path("/denied")
    @DenyAll
    public String greetingDenied() { return "You will never see this"; }

    @GET
    @Path("/authenticated")
    @Authenticated // same as @RolesAllowed("*") - provided by Quarkus not the Java EE Security spec
    public String greetingAuthenticated() { return "Even Alex can see this :D"; }
}
