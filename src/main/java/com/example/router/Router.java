package com.example.router;

import com.example.DependencyInjectionModule;
import com.google.common.net.MediaType;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import spark.*;
import spark.servlet.SparkApplication;

import javax.servlet.http.HttpServletResponse;

@Slf4j
public class Router implements SparkApplication {
    @Override
    public void init() {

        final Injector injector = DependencyInjectionModule.getInstance();

        Spark.before((request, response) -> request.body());

        Spark.after((request, response) -> setHeaders(response));

        Spark.get("/ping", (request, response) -> {

            response.status(HttpServletResponse.SC_OK);
            response.header("Content-Type", MediaType.PLAIN_TEXT_UTF_8.toString());

            return "pong";
        });

        configureEndpoints();
    }

    private void configureEndpoints() {
    }

    private void setHeaders(final Response response) {
        if (!response.raw().containsHeader("Content-Type")) {
            response.header("Content-Type", MediaType.JSON_UTF_8.toString());
        }

        response.header("Vary", "Accept,Accept-Encoding");
        response.header("Cache-Control", "max-age=0");
    }
}
