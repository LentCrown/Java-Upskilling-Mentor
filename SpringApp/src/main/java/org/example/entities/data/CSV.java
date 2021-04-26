package org.example.entities.data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.entities.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

@Service
@PropertySource("classpath:csv.properties")
public class CSV implements QuestionsDao {

    @Value(value = "${csv.separator}")
    private char separator;
    @Value(value = "${csv.ignore_quotations}")
    private boolean ignore_quotations;
    private Reader reader = null;
    private CSVReader csvReader = null;

    public CSV(){
    }

    private void setReader(Reader reader) {
        this.reader = reader;
    }

    private void setCsvReader(CSVReader csvReader) { this.csvReader = csvReader; }

    private void openFile(String path) {
        try {
            setReader(Utils.readFile(path));
        } catch (IOException e) {
            System.out.println(e.toString());
            this.reader = null;
            return;
        }
        setCsvReader(Utils.getCsvReader(reader, separator, ignore_quotations));
    }

    private void closeFile() {
        try {
            this.reader.close();
            this.csvReader.close();
            this.reader = null;
            this.csvReader = null;
        } catch (IOException e) {
            System.out.println(e.toString());
        }
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
}