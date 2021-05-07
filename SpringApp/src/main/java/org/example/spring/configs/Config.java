package org.example.spring.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:prod.properties")
public class Config {

    //@Value("${csv.separator}")
    private char separator = ';';
    //@Value("${csv.ignore_quotations}")
    private boolean ignore_quatations = true;
    //@Value("${csv.path}")
    private String path = "Example1.csv";
    //@Value("${report.pass_border}")
    private float pass_border = 70;

    public Config(){}

    public char getSeparator() { return separator;}

    public boolean isIgnore_quatations() { return ignore_quatations;}

    public String getPath() { return path;}

    public float getPass_border() {return pass_border;}
}