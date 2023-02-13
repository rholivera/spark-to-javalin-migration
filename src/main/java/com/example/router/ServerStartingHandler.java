package com.example.router;

import com.example.service.interfaces.DataLoader;
import io.javalin.core.event.EventHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class ServerStartingHandler implements EventHandler {

    private List<DataLoader> loaders;

    @Override
    public void handleEvent() throws Exception {
        log.info("Running ServerStartingHandler..");

        initialDataLoad();
        startDataSchedule();

        log.info("ServerStartingHandler completed");
    }

    private void initialDataLoad() {

    }

    private void startDataSchedule() {

    }

}