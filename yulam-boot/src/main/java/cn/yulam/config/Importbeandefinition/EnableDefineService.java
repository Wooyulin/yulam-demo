package cn.yulam.config.Importbeandefinition;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({LoggerDefinitionRegistrar.class}) //
public @interface EnableDefineService {
    //配置一些方法
    Class<?>[] exclude() default {};
}