package cn.yulam.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author 5yl
 * date: 2022/3/18
 */
//@Import(OtherConfig.class)
@Configuration
public class SpringConfig {
    @Bean
    public DefaultBean defaultBean() {
        return new DefaultBean();
    }

    @Bean
    public Caffeine caffeineConfig() {
        return
                Caffeine.newBuilder()

                        .maximumSize(10000).
                        expireAfterWrite(60, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);

        return caffeineCacheManager;
    }

}
