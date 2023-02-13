package com.example;

import com.example.config.SwaggerConfig;
import com.google.common.net.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class Main {
    private static final int DEFAULT_PORT = 8080;
    private static final String JAVALIN_PORT = "javalin.port";

    public static void main(final String[] args) {
        String prop = System.getProperty(JAVALIN_PORT);
        int port = StringUtils.isNotBlank(prop) ? Integer.parseInt(prop) : DEFAULT_PORT;

        WebServer app = new WebServer(config -> {
            config.defaultContentType = MediaType.JSON_UTF_8.toString();
            config.registerPlugin(SwaggerConfig.getOpenApiOptions());
        });
        app.start(port);

        log.info("Listening on http://localhost:{}/", port);
    }

}
