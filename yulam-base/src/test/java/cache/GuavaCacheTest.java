package cache;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCacheTest {

    @Test
    public void testClassLoader() throws ExecutionException {
        LoadingCache<String, String> clCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(new CacheLoader<String, String>() {
                            @Override
                            public String load(String key) throws Exception {
                                System.out.println(key + "开始计算");
                                return "cache" + key;
                            }
                        });
        System.out.println(clCache.get("key1"));
        System.out.println(clCache.get("key1"));
        System.out.println(clCache.get("key1"));
        System.out.println(clCache.get("key2"));
        System.out.println(clCache.get("key2"));
        System.out.println(clCache.get("key2"));
        ImmutableMap<String, String> all = clCache.getAll(Arrays.asList("key1", "key2"));
        all.forEach((key, val) -> {
            System.out.println(key + "==>" + val);
        });
    }

    @Test
    public void testBasedEviction() {
        TestTicker myTicker = new TestTicker();
        LoadingCache<String, String> clCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .ticker(myTicker)
                .expireAfterAccess(30, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println(key + "开始计算");
                        return "cache" + key;
                    }
                });

        System.out.println(clCache.getUnchecked("key1"));
        System.out.println(clCache.getUnchecked("key1"));
        myTicker.addElapsedTime(TimeUnit.NANOSECONDS.convert(30, TimeUnit.SECONDS));
        System.out.println(clCache.getUnchecked("key1"));

    }

    @Test
    public void testRemovalListener() throws InterruptedException {
        TestTicker myTicker = new TestTicker();
        LoadingCache<String, String> clCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .ticker(myTicker)
                .removalListener(notification -> System.out.println(String
                        .format("缓存 %s 因为 %s 失效了，它的value是%s", notification.getKey(), notification.getCause(),
                                notification.getValue())))
                .expireAfterAccess(30, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println(key + "开始计算");
                        return "cache" + key;
                    }
                });
        System.out.println(clCache.getUnchecked("key1"));
        System.out.println(clCache.getUnchecked("key1"));
        myTicker.addElapsedTime(TimeUnit.NANOSECONDS.convert(30, TimeUnit.SECONDS));
        clCache.invalidate("key1"); // 主动过期，会立马触发listener
        Thread.sleep(3000);
        System.out.println("等了三秒"); //这样可以看到 listener 的输出会在这里之后，代表是访问之后去判断这个缓存是否应该失效
        System.out.println(clCache.getUnchecked("key1"));


    }

    private static class TestTicker extends Ticker {
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
