package org.mentor.service;

import org.mentor.config.SourcePathConfig;
import org.mentor.dao.CSVFile;
import org.mentor.entity.Answer;
import org.mentor.entity.Question;
import org.mentor.entity.Report;
import org.mentor.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class Application {
    private CSVFile csvFile;
    private User user;
    private SourcePathConfig sourcePathConfig;

    public Application(CSVFile csvFile, User user, SourcePathConfig sourcePathConfig){
        this.csvFile = csvFile;
        this.user = user;
        this.sourcePathConfig = sourcePathConfig;
    }

    public void run() {
        String input, source = sourcePathConfig.getSource();
        Report report = user.getReport();
        List<Question> questionList = csvFile.getQuestions(source);
        List<Answer> answerList = csvFile.getAnswers(source);
        Scanner scan = new Scanner(System.in);
        while (true) {
            printWelcome();

            input = scan.nextLine();
            if (input.equals("quit")) break;
            int question_number = 0, skipped = 0, answered = 0;
            user.reset(input);
            report.setTotal(questionList.size());

            printBeforeQuestions(input);
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

    private void printWelcome(){
        System.out.println("Welcome to questionnaire application!\n" +
                "You will answer on questions lists. " +
                "Enter your name or type 'quit' to close application");
        System.out.print("name> ");
    }

    private void printBeforeQuestions(String name){
        System.out.println();
        System.out.println("Welcome, " + name +"! Prepare to answer on some questions...");
    }

}
