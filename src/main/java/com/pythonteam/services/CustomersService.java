package com.pythonteam.services;

import com.pythonteam.databases.CustomerHandler;
import com.pythonteam.databases.EmployeeHandler;
import com.pythonteam.models.Customer;
import com.pythonteam.models.Employee;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomersService implements ServiceInterface<Customer> {
    @Override
    public Response create(Customer employee) {
        Customer response = new CustomerHandler().create(employee);
        if (response != null)
            return  Response.ok(true, MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response read(int id) {
        Customer employee = new CustomerHandler().findOne(id);
        if (employee == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(employee, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response readAll() {
            return Response.ok(new CustomerHandler().findAll(), MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response update(Customer employee) {
        return  Response.ok(new CustomerHandler().update(employee), MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response delete(int id) {
        boolean response = new CustomerHandler().delete(id);
        if (response){
            return  Response.ok(true, MediaType.APPLICATION_JSON).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}
