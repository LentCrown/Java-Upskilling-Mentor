package org.example;

import org.example.config.AppConfig;
import org.example.entities.Answer;
import org.example.entities.Question;
import org.example.entities.Report;
import org.example.entities.User;
import org.example.dao.CSVFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class App {
    private String source;
    private CSVFile csvFile;
    private User user;
    private AppConfig appConfig;

    public App(CSVFile csvFile, User user, AppConfig appConfig){
        this.csvFile = csvFile;
        this.user = user;
        this.appConfig = appConfig;
    }

    public void start() {
        String input;
        Report report = user.getReport();
        List<Question> questionList = csvFile.getQuestions(source);
        List<Answer> answerList = csvFile.getAnswers(source);
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to questionnaire application!\n" +
                    "You will answer on questions lists. " +
                    "Enter your name or type 'quit' to close application");
            System.out.print("name> ");

            input = scan.nextLine();
            if (input.equals("quit")) break;
            int question_number = 0, skipped = 0, answered = 0;
            user.reset(input);
            report.setTotal(questionList.size());

            System.out.println();
            System.out.println("Welcome, " + input +"! Prepare to answer on some questions...");
            System.out.println("###" + source + "###");

            for (Question question : questionList) {
                Answer answer = answerList.get(question_number);
                System.out.print(question.toString() + "> ");
                input = scan.nextLine();
                if (answer.getId() == question_number++){
                    if (input.isEmpty()){
                        answer.setAnswer("--skipped by user--");
                        skipped++;
                    }
                    else {
                        answer.setAnswer(input);
                        if (answer.getRight_answer().equals(input)) answered++;
                    }
                }
            }
            report.setAnswered(answered);
            report.setSkipped(skipped);
            report.process();
            System.out.println(user.toString());
        }
    }
}
