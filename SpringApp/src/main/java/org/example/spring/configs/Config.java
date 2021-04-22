package org.example.spring.configs;

import org.example.entities.data.CSV;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:csv.properties")
public class Config {

    @Value(value = "${csv.path}")
    private String path;

    @Bean
    @Lazy()
    CSV csv() {
        CSV csv = new CSV();
        csv.setPath(path,true);
        return csv;
    }
}
