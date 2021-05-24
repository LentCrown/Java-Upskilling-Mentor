package org.mentor.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {
    private static int instance_counter = 1;
    private static float pass_border;

    private int id;
    private Date date;
    private int total;
    private int answered;
    private int skipped;
    private Status status;

    public Report(int total, int answered, int skipped){
        this.id = instance_counter++;
        this.date = new Date();
        this.total = total;
        this.answered = answered;
        this.skipped = skipped;
        status = Status.FAILED;

    }
    public static void setPass_border(float pass_border) {
        Report.pass_border = pass_border;
    }

    public void process(){
        float result = ( (float) (answered) / (float) total) * 100;
        if (result >= pass_border) status = Status.PASSED;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat(ConstantValues.PATTERN_DATE);
        return "Date: " + dateFormat.format(date) + "\n" +
               "Answered correctly: " + answered + " / " + total + "\n" +
               "Skipped: " + skipped + " / " + total + "\n" +
               "Result: " + ((status == Status.PASSED) ? "PASSED" : "FAILED") + "\n";
    }
}
