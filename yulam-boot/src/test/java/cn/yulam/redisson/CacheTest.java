package cn.yulam.redisson;

import cn.yulam.Application;
import cn.yulam.service.CacheCCService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EnableCaching
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CacheTest {

   @Autowired
   private CacheManager cacheManager;
   @Autowired
   private CacheCCService cacheCCService;

   @Test
    public void put() {
       cacheManager.getCache("retCache").put("eh", "eee");
       String v = cacheManager.getCache("retCache").get("eh", String.class);
       Assertions.assertNotNull(v);
   }

   @Test
   public void testCache() {
      for (int i = 0; i < 10; i++) {
         System.out.println(cacheCCService.getCount());
      }
   }
}
