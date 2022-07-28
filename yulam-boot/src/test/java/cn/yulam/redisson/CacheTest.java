package cn.yulam.redisson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootTest
@EnableCaching
public class CacheTest {

   @Autowired
   private CacheManager cacheManager;

   @Test
    public void put() {
       cacheManager.getCache("retCache").put("eh", "eee");
       String v = cacheManager.getCache("retCache").get("eh", String.class);
       Assertions.assertNotNull(v);
   }
}
