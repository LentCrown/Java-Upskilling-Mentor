package org.example.spring.configs;

import org.example.entities.data.CSV;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:csv.properties")
public class Config {

    @Bean
    @Lazy()
    CSV csv() {
        return new CSV();
    }
}
