package com.example.router;

import io.javalin.core.event.EventHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ServerStartingHandler implements EventHandler {

    @Override
    public void handleEvent() throws Exception {
        log.info("Running ServerStartingHandler..");

        log.info("ServerStartingHandler completed");
    }

}