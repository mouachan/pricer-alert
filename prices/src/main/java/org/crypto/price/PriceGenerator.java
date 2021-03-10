package org.crypto.price;

import java.time.Duration;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.messaging.Message;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.crypto.model.Currency;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.UUID;
import io.smallrye.reactive.messaging.ce.OutgoingCloudEventMetadata;
import io.vertx.core.json.JsonObject;

/**
 * A bean producing random prices every 5 seconds.
 * The prices are written to a Kafka topic (prices). The Kafka configuration is specified in the application configuration.
 */
@ApplicationScoped
public class PriceGenerator {

    private Random random = new Random();

    @Outgoing("generated-price")
    @Broadcast 
    public Multi<Message<JsonObject>> generateCloudEvent() throws Exception{
        double leftLimit = 10D;
        double rightLimit = 100D;
        final URI source = new URI("local://timer");
        String [] paires = {"euro-dollar", "euro-yen", "euro-dinars", "euro-gb"};
        Currency currency = new Currency(paires[random.nextInt(paires.length)],(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit)));
        JsonObject json = new JsonObject().put("paire",currency.getPaire()).put("value",currency.getValue());
        return Multi.createFrom().ticks().every(Duration.ofSeconds(5))
        .onOverflow().drop()
        .map(tick -> Message.of(json).addMetadata(OutgoingCloudEventMetadata.builder()
                                    .withSource(URI.create("test://test"))
                                    .withType("price")
                                    .withId(UUID.randomUUID().toString())
                                    .withSubject("subject")
                                    .withTimestamp(ZonedDateTime.now())
                                    .withDataContentType("application/cloudevents+json; charset=UTF-8")
                                    //.withSpecVersion("0.3")
                                    .build()));
    }
}
