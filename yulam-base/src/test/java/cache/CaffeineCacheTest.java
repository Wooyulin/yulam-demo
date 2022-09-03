package cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.Ticker;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class CaffeineCacheTest {

    @Test
    public void testEvictTime() {
        TestTicker testTicker = new TestTicker();
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .evictionListener((key, graph, cause) ->
                        System.out.printf("Key %s was evicted (%s)%n", key, cause))
                .ticker(testTicker)
                .build(key -> {
                    System.out.println("build act" + key);
                    return "cache" + key;
                });
        System.out.println(cache.get("key1"));
        System.out.println(cache.get("key1"));
        testTicker.addElapsedTime(TimeUnit.NANOSECONDS.convert(40, TimeUnit.MINUTES));
        System.out.println(cache.get("key1"));



    }

    @Test
    public void testEvictManal() {
        TestTicker testTicker = new TestTicker();
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .evictionListener((key, graph, cause) ->
                        System.out.printf("Key %s was evicted (%s)%n", key, cause))
                .ticker(testTicker)
                .build();
        cache.put("key2", "val2");
        System.out.println(cache.getIfPresent("key2"));
        System.out.println(cache.getIfPresent("key2"));
//        System.out.println(cache.get("key1"));
//        System.out.println(cache.get("key1"));
        testTicker.addElapsedTime(TimeUnit.NANOSECONDS.convert(40, TimeUnit.MINUTES));
//        System.out.println(cache.get("key1"));
        System.out.println(cache.getIfPresent("key2"));




    }

    private static class TestTicker implements Ticker {
        private long start = Ticker.systemTicker().read();
        private long elapsedNano = 0;

        @Override
        public long read() {
            return start + elapsedNano;
        }

        public void addElapsedTime(long elapsedNano) {
            this.elapsedNano = elapsedNano;
        }
    }
}
