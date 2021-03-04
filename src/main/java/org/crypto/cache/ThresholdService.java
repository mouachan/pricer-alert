package org.crypto.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.crypto.model.Threshold;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import io.quarkus.runtime.StartupEvent;


@ApplicationScoped
public class ThresholdService {

    private static final Logger LOGGER = LoggerFactory.getLogger("ThresholdService");
    Cache<Object, Threshold>  thresholdCache;

    EmbeddedCacheManager cacheManager;

    public List<Threshold> getAll() { 
        return new ArrayList<>(thresholdCache.values());
    }

    public void save(Threshold entry) { 
        thresholdCache.put(getKey(entry), entry);
    }

    public void delete(Threshold entry) { 
        thresholdCache.remove(getKey(entry));
    }

    public void getEntry(Threshold entry){ 
        thresholdCache.get(getKey(entry));
    }

    public static String getKey(Threshold entry){
        return entry.getId()+","+entry.getValue();
    }

    public Threshold findById(String key) {
        LOGGER.info("findById "+key);
        return thresholdCache.get(key);
    }

    void onStart(@Observes StartupEvent ev) {
        GlobalConfigurationBuilder global = GlobalConfigurationBuilder.defaultClusteredBuilder();
        global.transport().clusterName("Pricer");
        cacheManager = new DefaultCacheManager(global.build());

        ConfigurationBuilder config = new ConfigurationBuilder();

        config.expiration().lifespan(5, TimeUnit.MINUTES)
                .clustering().cacheMode(CacheMode.REPL_SYNC);

        cacheManager.defineConfiguration("threshold", config.build());
        thresholdCache = cacheManager.getCache("threshold");
        thresholdCache.addListener(new CacheListener());
        Threshold threshold = new Threshold("anisse", 10, "euro-dollar");
        String key = "euro-dollar";
        thresholdCache.put(key, threshold);
        threshold = new Threshold("anisse", 10, "euro-dinars");
        key = "euro-dinars";
        thresholdCache.put(key, threshold);
        LOGGER.info("Cache initialized");
    }

    
}