package com.pythonteam.services;

import com.pythonteam.databases.TokenHandler;
import com.pythonteam.models.User;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/login")
public class TokensService implements ServiceInterface<User> {

    @Override
    public Response create(User user) {
        if (user == null)
        {
            return Response.serverError().entity("User no puede ir vacio").build();
        }
        try {
            return  Response.ok(new TokenHandler().create(user), MediaType.APPLICATION_JSON).build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response read(int id) {
        return null;
    }

    @Override
    public Response readAll() {
        return null;
    }

    @Override
    public Response update(User user) {
        return null;
    }

    @Override
    public Response delete(int id) {
        return null;
    }
}
