package org.mentor.service.parser;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.mentor.config.CSVConfig;
import org.mentor.domain.Answer;
import org.mentor.domain.Question;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

@Component
public class CsvFileParser {
    private CSVConfig csvConfig;

    CsvFileParser(CSVConfig config){
        this.csvConfig = config;
    }

    public List<Question> findAll(String source) {
        List<String[]> rawLines = readRawLinesFromCsvFile(source);
        if(rawLines.get(0).length<6){
            System.out.println("Error with reading '" + source + ".csv' file. Check it for right format...");
            return null;
        }
        List<Question> questionList = new ArrayList<>();
        for (String[] line: rawLines){
            Question question = new Question(line[0],new Answer(line[5]));
            getAnswers(question,line);
            questionList.add(question);
        }
        return questionList;
    }

    private void getAnswers(Question question, String[] line){
        AtomicBoolean no_choices = new AtomicBoolean(false);
        IntStream.rangeClosed(1, 4)
                .peek((i -> {
                    if(line[i].isEmpty()) no_choices.set(true);
                })).forEach(i -> {
                    if (!no_choices.get()) question.getAnswerList().add(new Answer(line[i]));
                });
    }

    public List<String[]> readRawLinesFromCsvFile(String source){
        ClassPathResource csvResource = new ClassPathResource(source);
        if (csvResource.exists()) {
            try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(csvResource.getInputStream()))
                    .withSkipLines(1)
                    .withCSVParser(new CSVParserBuilder()
                            .withSeparator(csvConfig.getSeparator())
                            .withIgnoreQuotations(csvConfig.isIgnore_quotations())
                            .build())
                    .build()) {
                return new ArrayList<>(reader.readAll());
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
