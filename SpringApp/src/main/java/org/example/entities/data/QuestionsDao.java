package org.example.entities.data;

import org.example.entities.Questions;

import java.util.List;

interface QuestionsDao {
    List<Questions> getQuestions(String source);
}
