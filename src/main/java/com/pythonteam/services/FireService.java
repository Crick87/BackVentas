package com.pythonteam.services;


import com.pythonteam.databases.FireHandler;
import com.pythonteam.models.Token;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/token")
public class FireService {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(Token token)
    {
        System.out.println("---------------------"+token);
        if (token.getToken().equals(""))
        {
            return Response.serverError().entity("No hay token").build();
        }
        return Response.ok(new FireHandler().add(token), MediaType.APPLICATION_JSON).build();
    }

}
