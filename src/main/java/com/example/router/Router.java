package com.example.router;

import com.example.DependencyInjectionModule;
import com.example.controller.ItemController;
import com.google.common.base.Stopwatch;
import com.google.inject.Injector;
import io.javalin.apibuilder.EndpointGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static io.javalin.apibuilder.ApiBuilder.get;

@Slf4j
public class Router implements EndpointGroup {
    @Override
    public void addEndpoints() {
        Injector injector = DependencyInjectionModule.getInstance();
        Stopwatch stopwatch = Stopwatch.createStarted();
        ItemController itemController = injector.getInstance(ItemController.class);

        long timeTakenToExecuteRouterInit = stopwatch.elapsed(TimeUnit.SECONDS);
        log.info("[init] Time taken to execute Router.init method: {}s", timeTakenToExecuteRouterInit);

        get("/items/{itemId}", itemController::getItemById);

    }
}
