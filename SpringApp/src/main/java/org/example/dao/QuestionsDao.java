package org.example.dao;

import org.example.entities.Answer;
import org.example.entities.Question;

import java.util.List;

interface QuestionsDao {
    List<Question> getQuestions(String source);
    List<Answer> getAnswers(String source);
}
