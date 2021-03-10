package org.crypto.price;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.awaitility.Awaitility.await;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.crypto.model.Currency;

@QuarkusTest
@QuarkusTestResource(KafkaResource.class)
class PriceResourceTest {

    private static final String PRICES_SSE_ENDPOINT = "http://localhost:8081/prices/stream";

    @Test
    void testPricesEventStream() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(PRICES_SSE_ENDPOINT);

        List<Currency> received = new CopyOnWriteArrayList<>();
        System.out.println(received);
        SseEventSource source = SseEventSource.target(target).build();
        source.register(inboundSseEvent -> received.add(inboundSseEvent.readData(Currency.class)));
        source.open();
        await().atMost(100000, MILLISECONDS).until(() -> received.size() == 3);
        System.out.println(received);
        source.close();
    }
}
