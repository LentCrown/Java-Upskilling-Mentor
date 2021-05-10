package org.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "application")
public class AppConfig {
    private String source;
    private char separator;
    private boolean ignore_quatations;
    private float pass_border;
}
