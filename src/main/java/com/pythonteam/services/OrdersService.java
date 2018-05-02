package com.pythonteam.services;

import com.pythonteam.databases.OrderHandler;
import com.pythonteam.databases.ProductHandler;
import com.pythonteam.models.Order;
import com.pythonteam.models.Product;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
public class OrdersService implements ServiceInterface<Order> {
    @Override
    public Response create(Order order) {
        Order response = new OrderHandler().create(order);
        if (response != null)
            return  Response.ok(true, MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response read(int id) {
        Order order = new OrderHandler().findOne(id);
        if (order == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(order, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response readAll() {
            return Response.ok(new OrderHandler().findAll(), MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response update(Order order) {
        return  Response.ok(new OrderHandler().update(order), MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response delete(int id) {
        boolean response = new OrderHandler().delete(id);
        if (response){
            return  Response.ok(true, MediaType.APPLICATION_JSON).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}