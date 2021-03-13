package com.adisava;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider // Sets this class as an extension interface
public class HeaderAdditionContainerResponseFilter implements ContainerResponseFilter {

     /*
    Command1: curl localhost:8080/ok -v
    Command2: curl -X GET "http://localhost:8080/hello-resteasy?order=asc" -H "accept: text/plain" -H "authorization: XYZ" -v

    Note that this is applied only to the first command (JAX-RS resources) and not in reactive routes (Command2)
     */

    @Override
    public void filter(ContainerRequestContext containerRequestContext,
                       ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders()
                .add("X-Header", "Header added by JAXRS Filter");
        // branch1stuff
        //otherstuf2
    }
}
