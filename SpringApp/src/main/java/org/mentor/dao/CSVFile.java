package org.mentor.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.mentor.config.CSVConfig;
import org.mentor.entity.Answer;
import org.mentor.entity.Question;
import org.mentor.utils.FileReader;
import org.mentor.utils.Parser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVFile implements QuestionDAO {
    private Reader reader;
    private CSVReader csvReader;
    private CSVConfig csvConfig;

    public CSVFile(CSVConfig config){
        reader = null;
        csvReader = null;
        this.csvConfig = config;
    }

    @Override
    public List<Question> getQuestions(String source) {
        List<String[]> raw = readCSV(source);
        if (raw == null) return null;
        int index = 0;
        List<Question> questionList = new ArrayList<>();
        List<String> questions = Parser.getColumn(raw,"Question");
        if (questions == null) return null;
        questions.remove(0);
        for(String question: questions){ questionList.add(new Question(index++,question)); }
        return questionList;
    }

    @Override
    public List<Answer> getAnswers(String source) {
        List<String[]> raw = readCSV(source);
        if (raw == null) return null;
        int index = 0;
        List<Answer> answerList = new ArrayList<>();
        List<String> answers = Parser.getColumn(raw,"Answer");
        if (answers == null) return null;
        answers.remove(0);
        for(String answer: answers){answerList.add(new Answer(index++,answer));}
        return answerList;
    }

    public List<String[]> readCSV(String source){
        source = FileReader.getResource(source);
        if (source==null) return null;
        openFile(source);
        List<String[]> lines = new ArrayList<>();
        try {
            lines = this.csvReader.readAll();
        } catch (IOException | CsvException e) {
            System.out.println(e);
        }
        closeFile();
        return lines;
    }

    private void openFile(String path) {
        try {
            reader = FileReader.readFile(path);
        } catch (IOException e) {
            System.out.println(e.toString());
            this.reader = null;
            return;
        }
        csvReader = Parser.getCsvReader(reader, csvConfig.getSeparator(), csvConfig.isIgnore_quotations());
    }

    private void closeFile() {
        try {
            this.reader.close();
            this.reader = null;
            this.csvReader.close();
            this.csvReader = null;
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
