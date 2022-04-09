package cn.yulam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author 5yl
 * date: 2022/3/30
 */
@SpringBootApplication
public class GateWayApplication {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/baidu").uri("http://baidu.com:80"))
                .build();

    }

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class);
    }
}
