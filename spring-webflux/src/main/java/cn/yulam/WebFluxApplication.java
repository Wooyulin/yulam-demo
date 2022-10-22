package cn.yulam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebFluxApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(WebFluxApplication.class, args);
    }
}
