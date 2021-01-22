package com.example;

import extensions.ApiTestExtension;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(ApiTestExtension.class)
class PingTest {
    @Test
    void ping() {
        final Response response = RestAssured.get("http://localhost:8080/ping");
        assertEquals(HttpServletResponse.SC_OK, response.getStatusCode());
        assertEquals("pong", response.getBody().asString());
    }
}