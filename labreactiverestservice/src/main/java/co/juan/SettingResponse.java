package co.juan;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;

import org.jboss.resteasy.reactive.ResponseHeader;
import org.jboss.resteasy.reactive.ResponseStatus;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

@Path("response")
public class SettingResponse {

    @GET
    @Path("/{name}")
    public RestResponse<String> hello(String name) {
        // HTTP OK status with text/plain content type
        return ResponseBuilder.ok("Hello, World!", MediaType.TEXT_PLAIN_TYPE)
                // set a response header
                .header("X-Cheese", "Camembert")
                // set the Expires response header to two days from now
                .expires(Date.from(Instant.now().plus(Duration.ofDays(2))))
                // send a new cookie
                .cookie(new NewCookie("Nombre", name))
                // end of builder API
                .build();
    }

    @GET
    @ResponseStatus(200)
    @ResponseHeader(name = "Juan", value = "Giraldo")
    public String hello(){
        return "Hello, World!";
    }
}