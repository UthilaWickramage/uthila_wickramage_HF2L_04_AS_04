package com.jiat.crud_application.config;

import org.glassfish.jersey.server.ResourceConfig;

public class AppConfig extends ResourceConfig {
    public AppConfig() {
        packages("com.jiat.crud_application.controllers");
packages("com.jiat.crud_application.middleware");
register(DependencyBinder.class);
    }
}
