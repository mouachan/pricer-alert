package org.crypto.cache;

import org.crypto.model.Threshold;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listener
public class CacheListener {
    private static final Logger LOGGER = LoggerFactory.getLogger("CacheListener");

    @CacheEntryCreated
    public void entryCreated(CacheEntryCreatedEvent<String, Threshold> event) {
        LOGGER.info("-- Entry for %s created \n", event.getType());
    }

    @CacheEntryModified
    public void entryUpdated(CacheEntryModifiedEvent<String, Threshold> event){
        LOGGER.info("-- Entry for %s modified\n", event.getType());
    }
}
