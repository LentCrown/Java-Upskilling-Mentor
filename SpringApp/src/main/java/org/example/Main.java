package org.example;

import org.example.config.AppConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan
@EnableConfigurationProperties(AppConfig.class)
public class Main {
    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
    public static void main(String[] args){
        ((App) context.getBean("app")).start();
        context.close();
    }
}
