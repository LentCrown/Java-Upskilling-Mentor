package org.example.spring.configs;

import org.example.data.entities.CSV;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class Config {
    @Bean
    @Lazy()
    CSV csv(String path) {
        return new CSV(path);
    }
}
