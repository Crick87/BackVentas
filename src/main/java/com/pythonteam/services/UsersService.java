package com.pythonteam.services;

import com.pythonteam.databases.UserHandler;
import com.pythonteam.filters.TokenSecured;
import com.pythonteam.models.User;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
}
