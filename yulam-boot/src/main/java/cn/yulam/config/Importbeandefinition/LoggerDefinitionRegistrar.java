package cn.yulam.config.Importbeandefinition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

public class LoggerDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //增加相关动态校验注入，我这里写死
        Class beanClass=LoggerService.class;
        RootBeanDefinition beanDefinition=new RootBeanDefinition(beanClass);
        String beanName= StringUtils.uncapitalize(beanClass.getSimpleName());
        registry.registerBeanDefinition(beanName,beanDefinition);
    }
}
