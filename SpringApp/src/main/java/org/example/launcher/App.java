package org.example.launcher;

import org.example.entities.Question;
import org.example.entities.Report;
import org.example.entities.User;
import org.example.entities.data.CSV;
import org.example.spring.configs.Config;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
@PropertySource("classpath:prod.properties")
public class App {
    private String source;
    private CSV csv;
    private User user;
    private Config config;

    public App(){
        csv = new CSV();
        user = new User();
        config = new Config();
        source = config.getPath();
    }

    public void run() {
        String input;
        Report report = user.getReport();
        List<String> answers = new ArrayList<>();
        List<Question> questionList = csv.getQuestions(source);
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
            report.setTotal(questionList.size());

            System.out.println();
            System.out.println("Welcome, " + input +"! Prepare to answer on some questions...");
            System.out.println("###" + source + "###");

            for (Question question : questionList) {
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
