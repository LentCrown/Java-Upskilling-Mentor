package org.mentor.dao;

import org.mentor.domain.Question;
import org.mentor.service.parser.CsvFileParser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionCsvDao implements QuestionDao {
    CsvFileParser csvFileParser;

    public QuestionCsvDao(CsvFileParser csvFileParser){
        this.csvFileParser = csvFileParser;
    }

    @Override
    public List<Question> findAll(String source) {
        return csvFileParser.findAll(source);
    }
}
