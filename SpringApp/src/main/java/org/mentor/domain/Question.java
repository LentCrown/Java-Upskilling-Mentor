package org.mentor.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private static int count_instance = 1;
    private static int choice_num = 1;

    private final int id;
    private final String title;
    private final List<Answer> choiceList;
    private final Answer correct_answer;

    public Question(String title, Answer correct_answer){
        this.id = count_instance++;
        this.title = title;
        choiceList = new ArrayList<>();
        this.correct_answer = correct_answer;
    }

    public List<Answer> getChoiceList() {
        return choiceList;
    }

    public Answer getCorrect_answer() {
        return correct_answer;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append("\n");
        choiceList.forEach(answer -> stringBuilder.append(choice_num++)
                                     .append(")").append(answer.toString()).append("\n"));
        choice_num = 1;
        return "Вопрос " + id + " : " + title + ((choiceList.isEmpty())? "" : stringBuilder.append("choice").toString());
    }
}
