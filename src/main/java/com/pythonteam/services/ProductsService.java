package com.pythonteam.services;

import com.pythonteam.databases.ProductHandler;
import com.pythonteam.models.Product;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

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

    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response loadImage(@FormDataParam("file") InputStream in,
                              @FormDataParam("file") FormDataContentDisposition info) throws IOException {
        File upload = new File(info.getFileName());

        if (upload.exists()){
            String message = "file: " + upload.getName() + " already exists";
            return Response.status(Response.Status.CONFLICT).entity(message).build();
        } else {
            Files.copy(in, upload.toPath());
            ProductHandler.loadImage(upload.toPath());
            return Response.status(Response.Status.OK).build();
        }
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
