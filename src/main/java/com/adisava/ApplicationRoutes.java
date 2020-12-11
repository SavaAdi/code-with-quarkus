package com.adisava;

import io.quarkus.vertx.web.Route;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationRoutes {

    public void routes(@Observes Router router) {
        /*
        Reactive route, command: curl http://localhost:8080/ok
         */

        router.get("/ok").handler(routingContext -> routingContext.response().end("OK from Route"));
    }

    /*
    Declarative style, command: curl localhost:8080/declarative/ok?name=Adi
     */

    @Route(path = "declarative/ok", methods = HttpMethod.GET)
    public void greetings(RoutingContext routingContext) {
        String name = routingContext.request().getParam("name");

        if (name == null) {
            name = "world";
        }

        routingContext.response().end("OK, " + name + " my friend");
    }
}
