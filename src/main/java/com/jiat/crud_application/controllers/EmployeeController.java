package com.jiat.crud_application.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class EmployeeController {
    @Path("/add_employee")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public String add(){
        return "Add Employee";
    }

    @Path("/delete_employee")
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    public String delete(){
        return "Delete Employee";
    }
}
