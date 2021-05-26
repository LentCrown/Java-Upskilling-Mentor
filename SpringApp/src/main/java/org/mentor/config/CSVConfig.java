package org.mentor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "application")
public class CSVConfig {
    private char separator;
    private boolean ignore_quotations;
}
