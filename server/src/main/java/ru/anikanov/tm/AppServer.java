package ru.anikanov.tm;


import ru.anikanov.tm.bootstrap.Bootstrap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

@ApplicationScoped
public class AppServer {

    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        initializer.addPackages(AppServer.class);
        SeContainer container = initializer.initialize();
        container.select(Bootstrap.class).get().init();

    }
}