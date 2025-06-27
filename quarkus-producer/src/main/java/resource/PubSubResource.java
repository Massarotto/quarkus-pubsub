package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import service.PubSubService;

@Path("/pubsub")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class PubSubResource {

    @Inject
    PubSubService pubSubService;

    @POST
    @Path("/send")
    public String send(String message) {
        pubSubService.publishMessage(message);
        return "Message sent!";
    }
}
