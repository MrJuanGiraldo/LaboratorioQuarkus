package co.juan;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("hello")
public class Endpoint {

    @Path("{name}/{age:\\d+}")
    @GET
    public String personalisedHello(String name, int age) {
        return "Hello " + name + " is your age really " + age + "?";
    }

    @GET
    public String genericHello() {
        return "Hello stranger";
    }
}