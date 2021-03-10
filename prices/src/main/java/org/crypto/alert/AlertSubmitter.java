package org.crypto.price;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.messaging.Message;

import io.smallrye.reactive.messaging.ce.IncomingCloudEventMetadata;


import javax.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;


@ApplicationScoped
public class AlertSubmitter {
    private static final Logger LOGGER = Logger.getLogger(AlertSubmitter.class);

    @Incoming("alert")                                     
    @Outgoing("alert-stream")                             
    @Broadcast                                              
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING) 
    public String process(Message<String> alert) {
        IncomingCloudEventMetadata<String> cloudEventMetadata = alert.getMetadata(IncomingCloudEventMetadata.class).orElseThrow(() -> new IllegalArgumentException("Expected a Cloud Event"));
        LOGGER.infof("Received Cloud Events (spec-version: %s): source:  '%s', type: '%s', subject: '%s' ",
            cloudEventMetadata.getSpecVersion(),
            cloudEventMetadata.getSource(),
            cloudEventMetadata.getType(),
            cloudEventMetadata.getSubject().orElse("no subject"));
        return alert.getPayload();
    }
}
