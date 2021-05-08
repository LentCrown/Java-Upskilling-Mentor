package org.example.entities;

import org.springframework.stereotype.Service;

@Service
public class Report {
    private int total;
    private int answered;
    private int skipped;
    private float passing_score = 70;
    private Status status;

    public Report(){
        total = 0;
        answered = 0;
        status = Status.FAILED;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setAnswered(Integer answered) {
        this.answered = answered;
    }

    public void setSkipped(int skipped) { this.skipped = skipped; }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void reset(){
        total = 0;
        skipped = 0;
        answered = 0;
        status = null;
    }

    public void process(){
        float result = ( (float) (answered) / (float) total) * 100;
        if (result >= passing_score) setStatus(Status.PASSED);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Answered correclty: ").append(answered).append(" / ").append(total).append("\n");
        string.append("Skipped: ").append(skipped).append(" / ").append(total).append("\n");
        string.append("Result: ").append((status == Status.PASSED) ? "PASSED" : "FAILED").append("\n");
        return string.toString();
    }
}
