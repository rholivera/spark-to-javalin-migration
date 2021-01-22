package com.example;

import com.example.router.Router;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import spark.Spark;

@Slf4j
public class Main {

    public static void main(final String[] args) {
        try {
            log.info("Application Started!");
            final String prop = System.getProperty("spark.port");
            final int port = StringUtils.isNotBlank(prop) ? Integer.parseInt(prop) : 8080;
            Spark.port(port);
            final Router router = new Router();
            router.init();
            log.info("Listening on port: {} ", port);
        } catch (Throwable t) {
            t.printStackTrace();
            log.error("An exception occurred while starting up the application",t);
        }
    }

}
