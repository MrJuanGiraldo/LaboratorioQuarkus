package co.juan;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/legumes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LegumeResource {

    @Inject
    LegumeService service;

    @GET
    public Response list() {
        return Response.ok(service.getLegumes()).build();
    }

    @POST
    public Response create(Legume legume){
        service.add(legume);
        return Response.ok(legume).build();
    }

    @DELETE
    public Response delete(Legume legume){
        service.delete(legume);
        return Response.ok(service.getLegumes()).build();
    }
}