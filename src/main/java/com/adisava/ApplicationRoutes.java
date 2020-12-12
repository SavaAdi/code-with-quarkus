package com.adisava;

import io.quarkus.runtime.configuration.ProfileManager;
import io.quarkus.vertx.http.runtime.filters.Filters;
import io.quarkus.vertx.web.Route;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationRoutes {

    String currentProfile = ProfileManager.getActiveProfile();

    /*
    Manually retrieve profile-bound application
    properties; you don’t need to qualify the property
    names with the %profile prefix
     */
    String appName = ConfigProvider.getConfig().getValue("quarkus.application.name", String.class);

    /*
    Seems like @Inject is not required here, but might be OK for marking the intention.
    Loads the profile specific property, no need to qualify the properties name with %profile prefix
     */
    @ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
    String appPort;

    public void routes(@Observes Router router) {
        /*
        Reactive route, command: curl http://localhost:8080/ok
         */

        router.get("/ok")
                .handler(routingContext -> routingContext.response().end("OK from Route"));

        router.get("/profile")
                .handler(routingContext -> routingContext.response().end("Current profile is " + currentProfile));

        router.get("/app-name")
                .handler(routingContext -> routingContext.response().end("Application name is " + appName));
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

    @Route(path = "port", methods = HttpMethod.GET)
    public void getPort(RoutingContext routingContext) {
        routingContext.response().end("Application port is " + appPort);
    }

    /*
    Command1: curl localhost:8080/ok -v
    Command2: curl -X GET "http://localhost:8080/hello-resteasy?order=asc" -H "accept: text/plain" -H "authorization: XYZ" -v
     */

    public void filters(@Observes Filters filters) {
        filters
                .register(routingContext -> {
                    routingContext.response()
                            .putHeader("V-Header", "Header added by this VertX Filter");
                    routingContext.next();
                }, 10);
    }
}
