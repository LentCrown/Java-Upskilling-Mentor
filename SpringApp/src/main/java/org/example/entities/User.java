package org.example.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class User {
    private String name;
    @Autowired
    private Report report;

    public User() {}

    public void setName(String name) {
        this.name = name;
    }

    public void writeReport(Integer skipped, Integer total){
        report.setTotal(total);
        report.setAnswered(total - skipped);
        float result = ((float) skipped/ (float) total)*100;
        if (result<=report.getPassing_score()) report.setStatus(Status.PASSED);
        report.setStatus(Status.FAILED);
    }

    public void nullify(){
        name=null;
        report.nullify();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("\nUSER: " + name + "\n");
        string.append(report.toString());
        return string.toString();
    }
}




