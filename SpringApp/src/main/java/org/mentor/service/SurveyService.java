package org.mentor.service;

import org.mentor.config.ReportConfig;
import org.mentor.config.SourcePathConfig;
import org.mentor.dao.QuestionDao;
import org.mentor.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SurveyService implements IService {
    private final SourcePathConfig sourcePathConfig;
    private final ReportConfig reportConfig;
    private final QuestionDao questionDao;
    private final List<Answer> userAnswerList;
    private final List<Report> reportList;
    private String userSurname;
    private String userName;

    public SurveyService(SourcePathConfig sourcePathConfig, ReportConfig reportConfig, QuestionDao questionDao){
        this.sourcePathConfig = sourcePathConfig;
        this.reportConfig = reportConfig;
        this.questionDao = questionDao;
        userAnswerList = new ArrayList<>();
        reportList = new ArrayList<>();
        userSurname = "";
        userName = "";
    }

    @Override
    public void run() {
        boolean keepSameUser;
        Report.setPass_border(reportConfig.getPass_border());
        List<Question> questionList = questionDao.findAll(sourcePathConfig.getSource());
        if (questionList==null) return;
        while (true) {
            keepSameUser = true;
            showMenu();
            if (enterUserCredentials()) break;
            while (keepSameUser){
                printBeforeQuestions();
                processInput(questionList);
                processResults(questionList);
                keepSameUser = askUserForNewSurvey();
            }
        }
    }

    private void processInput(List<Question> questionList){
        String answer, regex = ConstantValues.REGEX_QUESTIONS_WITH_CHOICE;
        Scanner scan = new Scanner(System.in);
        for (Question question : questionList) {
            if (question.getChoiceList().isEmpty()){
                regex = ConstantValues.REGEX_QUESTIONS;
            }
            while (true){
                System.out.print(question.toString() + "> ");
                answer = scan.nextLine();
                if (!answer.matches(regex)) continue;
                userAnswerList.add(new Answer(answer));
                break;
            }
        }
    }

    private void processResults(List<Question> questionList){
        int total = questionList.size(), index, answered, skipped;
        index = answered = skipped = 0;
        for (Answer userAnswer : userAnswerList) {
            Answer correct_answer = questionList.get(index++).getCorrect_answer();
            if (userAnswer.getAnswer().isEmpty()){
                skipped++;
                continue;
            }
            if (userAnswer.equals(correct_answer)) answered++;
        }
        printResults(total,answered, skipped);
    }

    private void showMenu(){
        System.out.println("Welcome to survey application!\n" +
                "You will answer on questions lists. " +
                "Register yourself or type 'quit' to close this app");
    }

    private boolean enterUserCredentials(){
        String input;
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("name> ");
            input = scan.nextLine();
            if (input.matches(ConstantValues.REGEX_QUIT)) return true;
            if (input.matches(ConstantValues.REGEX_WORDS)) {
                userName = input;
                break;
            }
            System.out.println("Wrong name format, try again..");
        }
        while (true){
            System.out.print("surname> ");
            input = scan.nextLine();
            if (input.matches(ConstantValues.REGEX_QUIT)) return true;
            if (input.matches(ConstantValues.REGEX_WORDS)) {
                userSurname = input;
                break;
            }
            System.out.println("Wrong surname format, try again..");
        }
        return false;
    }

    private void printBeforeQuestions(){
        System.out.println();
        System.out.println(userSurname +" "+ userName +", prepare to answer on survey ("
                +sourcePathConfig.getSource()+")...");
    }

    private boolean askUserForNewSurvey(){
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.println("Do you want to continue as " + userSurname + " " + userName + "? (y/n)");
            System.out.print("cmd>");
            String command = scan.nextLine();
            if (!command.matches(ConstantValues.REGEX_YES) && !command.matches(ConstantValues.REGEX_NO))
                System.out.println("Wrong input, try again..");
            else {
                if (command.matches(ConstantValues.REGEX_YES)) return true;
                if (command.matches(ConstantValues.REGEX_NO)){
                    reportList.clear();
                    userName = userSurname = "";
                    System.out.println();
                    return false;
                }
            }
        }
    }

    private void printResults(int total, int answered, int skipped){
        userAnswerList.clear();

        StringBuilder stringBuilder = new StringBuilder();
        reportList.add(new Report(total,answered,skipped));
        reportList.forEach(report -> stringBuilder.append(report.toString()).append("\n"));
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        System.out.println(
                "**************************" + "\n" +
                "User: " + userSurname + " " + userName + "\n" +
                "**************************" + "\n" +
                stringBuilder.toString() +
                "**************************" + "\n");
    }
}
