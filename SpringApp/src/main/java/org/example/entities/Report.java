package org.example.entities;

import org.example.spring.configs.Config;
import org.springframework.stereotype.Service;

@Service
public class Report {
    private int total;
    private int answered;
    private float passing_score;
    private Status status;
    private Config config;

    public Report(){
        total = 0;
        answered = 0;
        status = Status.FAILED;
        config = new Config();
        passing_score = config.getPass_border();
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setAnswered(Integer answered) {
        this.answered = answered;
    }

    public void setPassing_score(float passing_score) { this.passing_score = passing_score; }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void reset(){
        total = 0;
        answered = 0;
        status = null;
    }

    public void process(){
        float result = ((float) answered / (float) total) * 100;
        if (result >= passing_score) setStatus(Status.PASSED);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Answered " + answered + " from " + total + "\n");
        string.append("Test is " + ((status==Status.PASSED)?"Passed":"Failed") + "\n");
        return string.toString();
    }
}
