package co.juan;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/hello")
public class GreetingResource {

    private static final Logger LOG = Logger.getLogger(GreetingResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        LOG.info("The hello resosurce works fine");
        LOG.error("The hello resosurce works fine");
        LOG.debug("The hello resosurce works fine");
        LOG.warn("The hello resosurce works fine");
        LOG.fatal("The hello resosurce works fine");

        return "Hello from RESTEasy Reactive";
    }
}