package com.adisava;

import com.adisava.service.SalutationsService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@QuarkusTest
class CoderResourceTest {

    @InjectMock
    SalutationsService salutationsService;

    @BeforeEach
    public void prepareMocks() {
        when(salutationsService.message())
                .thenReturn("Aloha from Mockito");
    }

    @Test
    void saluteCoder() {
        given()
                .when().get("/coder/salute")
                .then()
                .statusCode(200)
                .body(is("Aloha from Mockito"));
    }
}