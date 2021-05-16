package org.example.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.config.AppConfig;
import org.example.entities.Answer;
import org.example.entities.Question;
import org.example.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

@Component
public class CSVFile implements QuestionsDao {
    private char separator = ';';
    private boolean ignore_quotations = false;
    private Reader reader;
    private CSVReader csvReader;
    private AppConfig appConfig;

    public CSVFile(AppConfig appConfig){
        reader = null;
        csvReader = null;
        this.appConfig = appConfig;
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public boolean isIgnore_quotations() {
        return ignore_quotations;
    }

    public void setIgnore_quotations(boolean ignore_quotations) {
        this.ignore_quotations = ignore_quotations;
    }

    public List<String[]> readCSV(String source){
        source = Utils.getResource(source);
        if (source==null)
            return null;
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

    @Override
    public List<Question> getQuestions(String source) {
        List<String[]> raw = readCSV(source);
        int index = 0;
        List<Question> questionList = new ArrayList<>();
        List<String> questions = Utils.getColumn(raw,"Question");
        questions.remove(0);
        for(String question: questions){
            questionList.add(new Question(index++,question));
        }
        return questionList;
    }

    @Override
    public List<Answer> getAnswers(String source) {
        int index = 0;
        List<Answer> answerList = new ArrayList<>();
        List<String> answers = Utils.getColumn(readCSV(source),"Answer");
        answers.remove(0);
        for(String answer: answers){
            answerList.add(new Answer(index++,answer));
        }
        return answerList;
    }

    private void openFile(String path) {
        try {
            reader = Utils.readFile(path);
        } catch (IOException e) {
            System.out.println(e.toString());
            this.reader = null;
            return;
        }
        csvReader = Utils.getCsvReader(reader, separator, ignore_quotations);
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