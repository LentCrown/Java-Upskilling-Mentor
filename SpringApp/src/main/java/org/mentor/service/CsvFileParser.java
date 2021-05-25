package org.mentor.service;

import org.mentor.domain.Answer;
import org.mentor.domain.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

@Service
public class CsvFileParser implements ICsvParser {
    private final ICsvReader csvReader;

    CsvFileParser(ICsvReader csvReader){ this.csvReader = csvReader; }

    @Override
    public List<Question> findAll(String source) {
        List<String[]> rawLines = csvReader.readRawLines(source);
        if(rawLines == null || checkAllLinesLength(rawLines)){
            System.out.println("Error with reading '" + source + ".csv' file. Check file or format..");
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
                    if (!no_choices.get()) question.getChoiceList().add(new Answer(line[i]));
                });
    }

    private boolean checkAllLinesLength(List<String[]> rawLines){
        final boolean[] wrongFormat = {false};
        rawLines.forEach(line -> { if (line.length<6) wrongFormat[0] = true; });
        return wrongFormat[0];
    }
}
