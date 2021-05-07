package org.example.spring.configs;

import org.example.launcher.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:prod.properties")
public class Config {

    @Autowired
    Environment env;

    @Bean
    App app(){
        App app = new App();
        app.setSource(env.getProperty("csv.path"));
        app.setCsv(env.getProperty("csv.separator").charAt(0), Boolean.parseBoolean(env.getProperty("csv.ignore_quotations")));
        app.setTestPassBorder(Float.parseFloat(env.getProperty("report.passing_score")));
        return app;
    }
}
