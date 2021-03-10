package org.crypto.jsonb;

import org.crypto.model.Currency;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class CurrencyDeserializer extends JsonbDeserializer<Currency> {
    public CurrencyDeserializer(){
        // pass the class to the parent.
        super(Currency.class);
    }
}