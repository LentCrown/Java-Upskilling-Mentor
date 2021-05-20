package org.mentor.entity;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String name;
    private Report report;
    //TODO: Коллекция отчетов?

    public User(Report report) {
        name = null;
        this.report = report;
    }

    public void setName(String name) { this.name = name;}
    public Report getReport(){
        return report;
    }

    public void reset(){
        this.name = null;
        report.reset();
    }

    @Override
    public String toString() {
        return "\nUSER: " + name + "\n" + report.toString();
    }
}
