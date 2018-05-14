package com.pythonteam.services;

import com.pythonteam.databases.EmployeeHandler;
import com.pythonteam.models.Employee;
import com.pythonteam.models.Route;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/employees")
public class EmployeesService implements ServiceInterface<Employee> {
    @Override
    public Response create(Employee employee) {
        Employee response = new EmployeeHandler().create(employee);
        if (response != null)
            return  Response.ok(true, MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response read(int id) {
        Employee employee = new EmployeeHandler().findOne(id);
        if (employee == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(employee, MediaType.APPLICATION_JSON).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/routes/{id}")
    public Response readRoutes(@PathParam("id")  int id) {
        ArrayList<Route> routes = new EmployeeHandler().findRoute(id);
        if (routes == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(routes, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response readAll() {
            return Response.ok(new EmployeeHandler().findAll(), MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response update(Employee employee) {
        return  Response.ok(new EmployeeHandler().update(employee), MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response delete(int id) {
        boolean response = new EmployeeHandler().delete(id);
        if (response){
            return  Response.ok(true, MediaType.APPLICATION_JSON).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}
