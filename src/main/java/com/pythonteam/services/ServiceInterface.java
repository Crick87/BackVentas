package com.pythonteam.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public interface ServiceInterface<T> {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response create(T t);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    Response read(@PathParam("id") int id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response readAll();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(T t);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    Response delete(@PathParam("id") int id);
}
