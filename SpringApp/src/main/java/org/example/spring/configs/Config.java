package org.example.spring.configs;

import org.example.entities.Report;
import org.example.entities.User;
import org.example.entities.data.CSV;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:csv.properties")
public class Config {

    @Bean
    @Lazy()
    CSV csv() {
        return new CSV();
    }

    @Bean
    @Lazy()
    User user(){ return new User();}

    @Bean
    @Lazy()
    Report report(){ return new Report();}
}
