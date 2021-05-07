package org.example.entities.data;

import org.example.entities.Question;

import java.util.List;

interface QuestionsDao {
    List<Question> getQuestions(String source);
}
