package org.mentor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "test")
public class ReportConfigTest {
    private float pass_border;
}
