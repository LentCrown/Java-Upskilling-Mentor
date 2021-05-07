package org.example.entities.data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.entities.Question;
import org.example.spring.configs.Config;
import org.example.utils.Utils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

@Service
public class CSV implements QuestionsDao {
    private char separator;
    private boolean ignore_quotations;
    private Reader reader;
    private CSVReader csvReader;
    private Config config;

    public CSV(){
        reader = null;
        csvReader = null;
        config = new Config();
        separator = config.getSeparator();
        ignore_quotations = config.isIgnore_quatations();
    }

    public List<String[]> readRawLines(String source){
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

    public String read(String source){
        StringBuilder data = new StringBuilder();
        List<String[]> lines = readRawLines(source);
        if (lines==null)
            return null;
        for (String[] row: lines) {
            int size = row.length;
            int iter = 0;
            for (String column: row) {
                if (size==1 || iter==size-1)
                    data.append(column);
                else
                    data.append(column).append(separator);
                iter++;
            }
            data.append("\n");
        }
        return data.toString();
    }

    @Override
    public List<Question> getQuestions(String source){
        Deque<String[]> deque = new ArrayDeque<>(readRawLines(source));
        Integer orderNum = Utils.getColumnOrder(deque.getFirst(), "Question");
        if (orderNum==null)
            return null;
        deque.removeFirst();
        List<Question> questions = new ArrayList<>();
        int i=1;
        for (String[] line: deque){
            questions.add(new Question(i++,line[orderNum]));
        }
        return questions;
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