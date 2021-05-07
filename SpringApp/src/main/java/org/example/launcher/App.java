package org.example.launcher;

import org.example.entities.Questions;
import org.example.entities.Report;
import org.example.entities.User;
import org.example.entities.data.CSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private String source;
    private CSV csv;
    private User user;

    public App(){
        source = null;
        csv = new CSV();
        user = new User();
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setCsv(char separator, boolean ignore_quotations) {
        csv.setSeparator(separator);
        csv.setIgnore_quotations(ignore_quotations);
    }

    public void setTestPassBorder(float passBorder){
        Report report = user.getReport();
        report.setPassing_score(passBorder);
        user.setReport(report);
    }

    public void run() {
        String input;
        Report report = user.getReport();
        List<String> answers = new ArrayList<>();
        List<Questions> questionsList = csv.getQuestions(source);
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to questionnaire application!\n" +
                    "You will answer on questions lists. " +
                    "Enter your name or type 'quit' to close application");
            System.out.print("name> ");

            input = scan.nextLine();
            if (input.equals("quit")) break;
            int answered = 0;
            user.reset(input);
            report.setTotal(questionsList.size());

            System.out.println();
            System.out.println("Welcome, " + input +"! Prepare to answer on some questions...");
            System.out.println("###" + source + "###");

            for (Questions question : questionsList) {
                System.out.print(question.toString() + "> ");
                input = scan.nextLine();
                if (!input.isEmpty()) answered++;
                answers.add(input);
            }
            report.setAnswered(answered);
            report.process();
            System.out.println(user.toString());
        }
    }
}
