package org.crypto.price;

import io.smallrye.reactive.messaging.annotations.Channel;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.SseElementType;
import org.crypto.model.Currency;

/**
 * A simple resource retrieving the in-memory "my-data-stream" and sending the items as server-sent events.
 */
@Path("/price")
public class PriceResource {

    @Inject
    @Channel("price-stream") 
    Publisher<Currency> prices; 

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS) 
    //@SseElementType("application/json") 
    public Publisher<Currency> stream() { 
        return prices;
    }


}