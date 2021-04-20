package org.example.spring.configs;

import org.example.data.entities.CSV;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

@Configuration
@PropertySource("classpath:csv.properties")
public class Config {

    @Value(value = "${csv.path}")
    private String path;

    @Bean
    @Lazy()
    CSV basic() throws URISyntaxException {
        return new CSV(path);
    }

    @Bean
    @Lazy()
    CSV param(String parameter) throws URISyntaxException {
        return new CSV(parameter);
    }
}
