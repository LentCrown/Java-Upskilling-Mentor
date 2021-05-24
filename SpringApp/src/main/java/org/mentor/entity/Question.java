package org.mentor.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private static int count_instance = 1;
    private final int id;
    private final String title;
    private final List<Answer> answerList;
    private final Answer correct_answer;

    public Question(String title, Answer correct_answer){
        this.id = count_instance++;
        this.title = title;
        answerList = new ArrayList<>();
        this.correct_answer = correct_answer;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public Answer getCorrect_answer() {
        return correct_answer;
    }

    @Override
    public String toString() {
        return "Вопрос " + id + " : " + title + ((answerList.isEmpty())? "" : answerList.toString());
    }
}
