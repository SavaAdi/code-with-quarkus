package com.adisava;

import com.adisava.service.SalutationsServiceImpl;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        String authorization = "xyz";
        GreetingResource.Order order = GreetingResource.Order.asc;

        given()
          .when()
                .header("authorization", authorization)
                .queryParam("order", order.toString())
                .get("/hello-resteasy")
          .then()
             .statusCode(200)
             .body(containsString("Order " + order.toString() + " - Authorization: " + authorization));
    }

}