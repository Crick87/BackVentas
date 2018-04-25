package com.pythonteam.services;

import com.pythonteam.databases.ProductHandler;
import com.pythonteam.models.Product;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
public class ProductsService implements ServiceInterface<Product> {
    @Override
    public Response create(Product product) {
        Product response = new ProductHandler().create(product);
        if (response != null)
            return  Response.ok(true, MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response read(int id) {
        Product product = new ProductHandler().findOne(id);
        if (product == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(product, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response readAll() {
            return Response.ok(new ProductHandler().findAll(), MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response update(Product product) {
        return  Response.ok(new ProductHandler().update(product), MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response delete(int id) {
        boolean response = new ProductHandler().delete(id);
        if (response){
            return  Response.ok(true, MediaType.APPLICATION_JSON).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}
