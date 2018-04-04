package com.pythonteam.services;

import com.pythonteam.databases.EmployeeHandler;
import com.pythonteam.models.Employee;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/employees")
public class EmployeesService implements ServiceInterface<Employee> {
    @Override
    public Response create(Employee employee) {
        boolean response = new EmployeeHandler().create(employee);
        if (response)
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
