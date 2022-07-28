package cn.yulam;

import cn.yulam.config.Importbeandefinition.EnableDefineService;
import cn.yulam.config.Importbeandefinition.LoggerService;
import cn.yulam.config.importselector.CacheService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * @author 5yl
 * date: 2022/4/14
 */
@SpringBootApplication
@EnableCaching
//@EnableDefineService
@EnableDefineService(exclude = CacheService.class)
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
//        System.out.println(run.getBean(CacheService.class));
        System.out.println(run.getBean(LoggerService.class));

    }

}
