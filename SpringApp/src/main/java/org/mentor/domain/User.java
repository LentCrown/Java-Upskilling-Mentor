package org.mentor.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class User {
    private static int count_instance = 1;

    private int id;
    private String name;
    private String surname;
    private List<Report> reportList;

    public User() {
        id = count_instance++;
        name = "";
        surname = "";
        this.reportList = new ArrayList<>();
    }

    public void setName(String name) { this.name = name;}

    public void setSurname(String surname) {this.surname = surname;}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Report> getReportList(){
        return reportList;
    }

    public void reset(){
        id = count_instance = 1;
        this.name = null;
        this.surname = null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        reportList.forEach(report -> stringBuilder.append(report.toString()).append("\n"));
        return "**************************" + "\n" +
               "User: " + surname + " " + name + "\n" +
               "**************************" + "\n" +
                stringBuilder.toString() + "\n" +
                "**************************" + "\n";
    }
}
