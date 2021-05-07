package org.example.entities;

import org.springframework.stereotype.Service;

@Service
public class User {
    private String name;
    private Report report;
    //TODO: Коллекция отчетов?

    public User() {
        name = null;
        report = new Report();
    }

    public void setReport(Report report){
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
        string.append("\nUSER: " + name + "\n");
        string.append(report.toString());
        return string.toString();
    }
}




