package com.example;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DependencyInjectionModule extends AbstractModule {

    private static Injector INSTANCE;

    private DependencyInjectionModule() {
        super();
    }

    public static Injector getInstance() {
        if (INSTANCE == null) {
            INSTANCE = Guice.createInjector(new DependencyInjectionModule());
        }
        return INSTANCE;
    }

    @Override
    protected void configure() {
        log.info("Starting dependency injector");
        final String scope = System.getenv("SCOPE");
        log.info("Configuring environment: {}", scope);

        log.info("Finishing dependency injector");
    }
}
