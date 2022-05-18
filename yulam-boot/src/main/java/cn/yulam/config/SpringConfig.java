package cn.yulam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 5yl
 * date: 2022/3/18
 */
//@Import(OtherConfig.class)
@Configuration
public class SpringConfig {
    @Bean
    public DefaultBean defaultBean(){
        return new DefaultBean();
    }

}
