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

    @Value(value = "${csv.separator}")
    private char separator;
    @Value(value = "${csv.ignore_quotations}")
    private boolean ignore_quotations;

    @Bean
    @Lazy()
    CSV csv(String path) throws URISyntaxException {
        return new CSV(path);
    }
}
