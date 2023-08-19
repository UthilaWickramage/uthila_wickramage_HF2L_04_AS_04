package com.jiat.crud_application.config;

import com.jiat.crud_application.services.*;
import com.jiat.crud_application.util.JWTTokenUtil;
import jakarta.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class DependencyBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(JWTTokenUtil.class).to(JWTTokenUtil.class).in(Singleton.class);
        bind(UserService.class).to(UserService.class).in(Singleton.class);
        bind(EmployeeService.class).to(EmployeeService.class).in(Singleton.class);
    }
}
