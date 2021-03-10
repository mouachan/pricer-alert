package org.crypto.price;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.messaging.Message;
import java.lang.IllegalArgumentException;
import io.smallrye.reactive.messaging.ce.IncomingCloudEventMetadata;

import org.crypto.model.Currency;

import javax.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;
import io.smallrye.reactive.messaging.cloudevents.CloudEventMessage;
import java.util.Optional;
import io.vertx.core.json.JsonObject;


@ApplicationScoped
public class PriceSubmitter {
    private static final Logger LOGGER = Logger.getLogger(PriceSubmitter.class);

    @Incoming("price")                                     
    @Outgoing("price-stream")                             
    @Broadcast                                              
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING) 
    public  Currency process(Message<io.vertx.core.json.JsonObject> currency) {
        LOGGER.info("price submitter");
        IncomingCloudEventMetadata<io.vertx.core.json.JsonObject> cloudEventMetadata = currency.getMetadata(IncomingCloudEventMetadata.class).orElseThrow(() -> new IllegalArgumentException("Expected a Cloud Event"));
        LOGGER.infof("Received Cloud Events (spec-version: %s): source:  '%s', type: '%s', subject: '%s' ",
                    cloudEventMetadata.getSpecVersion(),
                    cloudEventMetadata.getSource(),
                    cloudEventMetadata.getType(),
                    cloudEventMetadata.getSubject().orElse("no subject")); 
        String paire = currency.getPayload().getString("paire"); 
        Double value = currency.getPayload().getDouble("value");        
        LOGGER.info(paire+ " "+value);
        return new Currency(paire,value);
   
    }
}
