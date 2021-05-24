package org.mentor.service;

import org.mentor.config.ReportConfig;
import org.mentor.config.SourcePathConfig;
import org.mentor.dao.QuestionDao;
import org.mentor.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class SurveyService implements IService {
    private SourcePathConfig sourcePathConfig;
    private ReportConfig reportConfig;
    private QuestionDao questionDao;
    private User user;
    private List<Answer> userAnswerList;

    public SurveyService(SourcePathConfig sourcePathConfig, ReportConfig reportConfig, QuestionDao questionDao, User user){
        this.sourcePathConfig = sourcePathConfig;
        this.reportConfig = reportConfig;
        this.questionDao = questionDao;
        this.user = user;
        userAnswerList = new ArrayList<>();
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
        List<Report> reportList = user.getReportList();
        for (Answer userAnswer : userAnswerList) {
            Answer correct_answer = questionList.get(index++).getCorrect_answer();
            if (userAnswer.getAnswer().isEmpty()){
                skipped++;
                continue;
            }
            if (userAnswer.equals(correct_answer)) answered++;
        }
        Report report = new Report(total,answered,skipped);
        report.process();
        reportList.add(report);
        System.out.println(user.toString());
        userAnswerList.clear();
    }

    private void showMenu(){
        System.out.println("Welcome to survey application!\n" +
                "You will answer on questions lists. " +
                "Register yourself or type 'quit' to close this app");
    }

    private boolean enterUserCredentials(){
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("name> ");
            String name = scan.nextLine();
            if (name.matches(ConstantValues.REGEX_QUIT)) return true;
            if (name.matches(ConstantValues.REGEX_WORDS)) {
                user.setName(name);
                break;
            }
            System.out.println("Wrong name format, try again..");
        }
        while (true){
            System.out.print("surname> ");
            String surname = scan.nextLine();
            if (surname.matches(ConstantValues.REGEX_QUIT)) return true;
            if (surname.matches(ConstantValues.REGEX_WORDS)) {
                user.setSurname(surname);
                break;
            }
            System.out.println("Wrong surname format, try again..");
        }
        return false;
    }

    private void printBeforeQuestions(){
        System.out.println();
        System.out.println(user.getSurname()+" "+user.getName()+", prepare to answer on survey ("
                +sourcePathConfig.getSource()+")...");
    }

    private boolean askUserForNewSurvey(){
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.println("Do you want to continue as " + user.getSurname() + " " + user.getName() + "? (y/n)");
            System.out.print("cmd>");
            String command = scan.nextLine();
            if (!command.matches(ConstantValues.REGEX_YES) && !command.matches(ConstantValues.REGEX_NO))
                System.out.println("Wrong input, try again..");
            else {
                if (command.matches(ConstantValues.REGEX_YES)) return true;
                if (command.matches(ConstantValues.REGEX_NO)){
                    user.getReportList().clear();
                    user.setName("");
                    user.setSurname("");
                    return false;
                }
            }
        }
    }
}
