package za.co.msrv.incubator.config;

import com.hazelcast.config.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {
    @Value("${cache.hazelcast.hero.ttl}")
    private int HERO_TTL;
    @Value("${cache.hazelcast.heroes.ttl}")
    private int HEROES_TTL;

    @Bean
    public EvictionConfig evictConfig() {
        return new EvictionConfig()
                .setEvictionPolicy(EvictionPolicy.LRU)
                .setMaxSizePolicy(MaxSizePolicy.PER_NODE)
                .setSize(500);
    }

    @Bean
    public Config hazelCastConfig(){
        return new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(
                    new MapConfig()
                        .setName("heroes")
                        .setEvictionConfig(evictConfig())
                        .setTimeToLiveSeconds(HEROES_TTL)
                )
                .addMapConfig(
                    new MapConfig()
                        .setName("hero")
                        .setEvictionConfig(evictConfig())
                        .setTimeToLiveSeconds(HERO_TTL)
                );
    }
}
