package org.mentor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "test")
public class CSVTestConfig {
    private char separator;
    private boolean ignore_quotations;
}
