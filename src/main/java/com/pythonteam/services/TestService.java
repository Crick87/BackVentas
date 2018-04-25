package com.pythonteam.services;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response test()
    {
        return Response.ok("User no puede ir vacio").build();
    }

}
