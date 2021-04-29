package org.example.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Report {

    private Integer total;
    private Integer answered;
    @Value(value = "${test.pass}")
    private float passing_score;
    private Status status;

    public Report(){}

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setAnswered(Integer answered) {
        this.answered = answered;
    }

    public float getPassing_score() {
        return passing_score;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void nullify(){
        total=null;
        answered=null;
        status=null;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Answered " + answered + " from " + total + "\n");
        string.append("Test is " + ((status==Status.PASSED)?"Passed":"Failed") + "\n");
        return string.toString();
    }
}
