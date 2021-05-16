package org.example.entities;

import org.example.App;
import org.example.config.AppConfig;
import org.springframework.stereotype.Service;

@Service
public class User {
    private String name;
    private Report report;
    //TODO: Коллекция отчетов?

    public User(Report report) {
        name = null;
        this.report = report;
    }

    public Report getReport(){
        return report;
    }

    public void reset(String name){
        this.name = name;
        report.reset();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("\nUSER: ").append(name).append("\n");
        string.append(report.toString());
        return string.toString();
    }
}




