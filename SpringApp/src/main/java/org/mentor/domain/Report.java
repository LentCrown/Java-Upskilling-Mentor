package org.mentor.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {
    private static int instance_counter = 1;
    private static float pass_border;

    private final int id;
    private final Date date;
    private final int total;
    private final int answered;
    private final int skipped;
    private Status status;

    public Report(int total, int answered, int skipped){
        this.id = instance_counter++;
        this.date = new Date();
        this.total = total;
        this.answered = answered;
        this.skipped = skipped;
        status = process();

    }
    public static void setPass_border(float pass_border) {
        Report.pass_border = pass_border;
    }

    public Status process(){
        float result = ( (float) (answered) / (float) total) * 100;
        if (result >= pass_border) return Status.PASSED;
        return Status.FAILED;
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
