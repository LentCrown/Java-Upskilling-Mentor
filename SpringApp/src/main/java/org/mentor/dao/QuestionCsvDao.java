package org.mentor.dao;

import org.mentor.domain.Question;
import org.mentor.service.ICsvParser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionCsvDao implements QuestionDao {
    private final ICsvParser csvFileParser;

    public QuestionCsvDao(ICsvParser csvFileParser){
        this.csvFileParser = csvFileParser;
    }

    @Override
    public List<Question> findAll(String source) {
        return csvFileParser.findAll(source);
    }
}
