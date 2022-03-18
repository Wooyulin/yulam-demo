package cn.yulam.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 5yl
 * date: 2022/3/18
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new
                AnnotationConfigApplicationContext(SpringConfig.class);
        String[] defNames=applicationContext.getBeanDefinitionNames();
        for(int i=0;i<defNames.length;i++){
            System.out.println(defNames[i]);
        }
    }
}
