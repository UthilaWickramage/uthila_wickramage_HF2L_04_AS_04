package com.jiat.crud_application.controllers;

import com.jiat.crud_application.util.HibernateUtil;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Path("/home")
public class HomeController {
    @GET
    public String get(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        return "This is Home";
    }
}
