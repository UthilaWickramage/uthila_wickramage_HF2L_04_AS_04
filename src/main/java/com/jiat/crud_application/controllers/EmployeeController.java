package com.jiat.crud_application.controllers;

import com.jiat.crud_application.entity.Employee;
import com.jiat.crud_application.services.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.swing.text.View;
import java.util.List;

@Singleton
@Path("/")
public class EmployeeController {
    @Inject
    EmployeeService employeeService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable index() {
        return new Viewable("/add");
    }

    @Path("/add_employee")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(@FormParam("name") String name, @FormParam("position") String position, @FormParam("department") String department, @FormParam("salary") String salary) {
        String result =  employeeService.insertEmployee(name, position, department, Double.valueOf(salary));
        if(result.equals("success")){
            return Response.ok().entity("Employee added successfuly").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Data").build();
        }
    }

    @Path("/search_employee")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public Viewable search(@FormParam("search") String search){
        List<Employee> employeeList = employeeService.getEmployeeListBySearch(search);
        if(employeeList.isEmpty()){
            return new Viewable("/index");
        }else{
            return new Viewable("/index",employeeList);
        }
    }

    @Path("/view_employee")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable displayAllEmployees() {
        List<Employee> list = employeeService.getEmployeeList();
        if(list.isEmpty()){
            return new Viewable("/index");
        }else{
            return new Viewable("/index", list);
        }

    }

    @Path("/view_update")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable dispay_update_page() {
        return new Viewable("/update");
    }


    @Path("/select_employee")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public Viewable select(@FormParam("search") String search) {
        Employee employee = employeeService.getEmployee(search);
        if(employee==null){
            Response.status(Response.Status.BAD_REQUEST).entity("Invalid Employee Id").build();
            return new Viewable("/update");
        }else{
            Response.ok().build();
            return new Viewable("/update", employee);
        }

    }

    @Path("/update_employee")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("id") String id, @FormParam("name") String name, @FormParam("position") String position, @FormParam("department") String department, @FormParam("salary") String salary) {
        String result = employeeService.updateEmployee(id, name, position, department, salary);
       if(result.equals("success")){
           return Response.ok().entity("Successfully Updated").build();
       }else{
           return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Employee Id").build();
       }

    }

    @Path("/delete_employee")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("id") Long id) {
        String result = employeeService.deleteEmployee(id);
        if (result.equals("success")) {
            return Response.ok().entity("Successfully Deleted").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invaild Employee Id").build();
        }
    }
}
