package org.mentor;

import org.mentor.config.CSVConfig;
import org.mentor.config.ReportConfig;
import org.mentor.config.SourcePathConfig;
import org.mentor.service.SurveyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties({SourcePathConfig.class, ReportConfig.class, CSVConfig.class})
public class Main {
    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(Main.class, args);
        ((SurveyService) context.getBean("surveyService")).run();
    }
}
