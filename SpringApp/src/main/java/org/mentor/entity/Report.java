package org.mentor.entity;

import org.mentor.config.ReportConfig;
import org.springframework.stereotype.Component;

@Component
public class Report {
    private int total;
    private int answered;
    private int skipped;
    private Status status;
    private ReportConfig reportConfig;

    public Report(ReportConfig reportConfig){
        total = 0;
        answered = 0;
        status = Status.FAILED;
        this.reportConfig = reportConfig;
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
        if (result >= reportConfig.getPass_border()) setStatus(Status.PASSED);
    }

    @Override
    public String toString() {
        return "Answered correctly: " + answered + " / " + total + "\n" +
                "Skipped: " + skipped + " / " + total + "\n" +
                "Result: " + ((status == Status.PASSED) ? "PASSED" : "FAILED") + "\n";
    }
}
