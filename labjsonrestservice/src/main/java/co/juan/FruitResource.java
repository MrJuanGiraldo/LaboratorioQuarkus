package co.juan;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.inject.Inject;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/fruits")
public class FruitResource {

    @Inject
    FruitService service;

    @GET
    public Set<Fruit> list() {
        return service.getFruits();
    }

    @POST
    public Set<Fruit> add(Fruit fruit) {
        service.add(fruit);
        return service.getFruits();
    }

    @DELETE
    public Set<Fruit> delete(Fruit fruit) {
        service.delete(fruit);
        return service.getFruits();
    }
}
