package org.mentor.service;

import org.mentor.config.SourcePathConfig;
import org.mentor.dao.CSVFile;
import org.mentor.dao.QuestionDAO;
import org.mentor.entity.Answer;
import org.mentor.entity.Question;
import org.mentor.entity.Report;
import org.mentor.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class Application {
    private SourcePathConfig sourcePathConfig;
    private QuestionDAO questionDAO;
    private User user;
    private List<String> userInputList;

    public Application(SourcePathConfig sourcePathConfig, QuestionDAO questionDAO, User user){
        this.sourcePathConfig = sourcePathConfig;
        this.questionDAO = questionDAO;
        this.user = user;
        userInputList = new ArrayList<>();
    }

    public void run() {
        String input;
        while (true) {
            input = printWelcome();
            if (input.equals("quit")) break;
            user.setName(input);
            printBeforeQuestions(input);
            processInput();
            System.out.println("###" + sourcePathConfig.getSource() + "###");
            processResults();
            user.reset();
            userInputList.clear();
        }
    }

    private void processInput(){
        String input;
        Scanner scan = new Scanner(System.in);
        List<Question> questionList = questionDAO.getQuestions(sourcePathConfig.getSource());
        for (Question question : questionList) {
            System.out.print(question.toString() + "> ");
            input = scan.nextLine();
            userInputList.add(input);
        }
    }

    private void processResults(){
        List<Answer> answerList = questionDAO.getAnswers(sourcePathConfig.getSource());
        Report report = user.getReport();
        report.setTotal(userInputList.size());

        int question_number, answered, skipped;
        question_number = answered = skipped = 0;
        for (String userInput : userInputList) {
            Answer answer = answerList.get(question_number);
            if (answer.getId() == question_number++){
                if (userInput.isEmpty()){
                    answer.setAnswer("--skipped by user--");
                    skipped++;
                }
                else {
                    answer.setAnswer(userInput);
                    if (answer.isCorrectAnswer()) answered++;
                }
            }
        }

        report.setAnswered(answered);
        report.setSkipped(skipped);
        report.process();
        System.out.println(user.toString());
    }

    private String printWelcome(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to questionnaire application!\n" +
                "You will answer on questions lists. " +
                "Enter your name or type 'quit' to close application");
        System.out.print("name> ");
        return scan.nextLine();
    }

    private void printBeforeQuestions(String name){
        System.out.println();
        System.out.println("Welcome, " + name +"! Prepare to answer on some questions...");
    }

}
