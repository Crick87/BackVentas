package com.pythonteam.services;

import com.pythonteam.databases.UserHandler;
import com.pythonteam.filters.TokenSecured;
import com.pythonteam.models.Customer;
import com.pythonteam.models.Route;
import com.pythonteam.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/users")
public class UsersService implements ServiceInterface<User> {

    @TokenSecured
    @Override
    public Response readAll() {
        return  Response.ok(new UserHandler().findAll(), MediaType.APPLICATION_JSON).build();
    }

    @TokenSecured
    @Override
    public Response create(User user) {
        User response = new UserHandler().create(user);
        if (response != null)
            return  Response.ok(true, MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @TokenSecured
    @Override
    public Response read(int id) {
        User user = new UserHandler().findOne(id);
        if (user == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(user, MediaType.APPLICATION_JSON).build();
    }

    @TokenSecured
    @Override
    public Response update(User user){
        return  Response.ok(new UserHandler().update(user), MediaType.APPLICATION_JSON).build();
    }

    @TokenSecured
    @Override
    public Response delete(int id) {
        boolean response = new UserHandler().delete(id);
        if (response){
            return  Response.ok(true, MediaType.APPLICATION_JSON).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/routes/{id}")
    public Response readRoutes(@PathParam("id")  int id) {
        ArrayList<Route> routes = new UserHandler().findRoute(id);
        if (routes == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(routes, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/routes")
    public Response readAllRoutes() {
        ArrayList<Route> routes = new UserHandler().findRoutes();
        if (routes == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(routes, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/routes")
    public Response updateRoute(Route route) {
        return Response.ok(new UserHandler().updateRoute(route), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/routes")
    public Response createRoute(Route route) {
        Route route1 = new UserHandler().createRoute(route);
        if (route1 == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(route1, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/routes")
    public Response delRoute(Route route) {
        boolean response = new UserHandler().delRoute(route.getIdEmployee(), route.getIdCustomer());
        if (!response)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers")
    public Response readCustomers() {
        ArrayList<Customer> routes = new UserHandler().readCustomers();
        if (routes == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(routes, MediaType.APPLICATION_JSON).build();
    }
}
