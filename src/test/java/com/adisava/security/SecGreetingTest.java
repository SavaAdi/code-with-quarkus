package com.adisava.security;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class SecGreetingTest {

    @Test
    public void testSecHello() {
        given()
                .auth()
                .basic("Adi", "testerpass")
                .when()
                .get("/sec-hello/secured")
                .then()
                .statusCode(200)
                .body(is("Adi just logged in!"));
    }

}