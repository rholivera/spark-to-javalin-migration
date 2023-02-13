package com.example;

import extensions.ApiTestExtension;
import io.javalin.testtools.JavalinTest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(ApiTestExtension.class)
class PingTest {
    @Test
    void ping() {
        WebServer app = new WebServer();
        JavalinTest.test(app.getServer(), (javalin, client) -> {
            Response response = client.get("/ping");
            assertEquals(HttpServletResponse.SC_OK, response.code());
            assertEquals("pong", response.body().string());
        });
    }
}