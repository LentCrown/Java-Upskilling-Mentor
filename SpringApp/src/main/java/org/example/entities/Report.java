package org.example.entities;

public class Report {
    private int total;
    private int answered;
    private float passing_score;
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
        if (result <= passing_score) setStatus(Status.FAILED);
        setStatus(Status.PASSED);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Answered " + answered + " from " + total + "\n");
        string.append("Test is " + ((status==Status.PASSED)?"Passed":"Failed") + "\n");
        return string.toString();
    }
}
