package co.juan;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.time.Duration;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

@Path("yawn")
public class ExecutionModel {

    @Blocking
    @GET
    public Uni<String> blockingHello() throws InterruptedException {
        // do a blocking operation
        Thread.sleep(1000);
        return Uni.createFrom().item("Yaaaawwwwnnnnnn…");
    }

    @GET
    @Path("nonblocking")
    public Uni<String> nonblockingHello() throws InterruptedException {
        return Uni.createFrom().item("Yaaaawwwwnnnnnn…")
                // do a non-blocking sleep
                .onItem().delayIt().by(Duration.ofSeconds(2));
    }
}