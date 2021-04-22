package org.example.entities.data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.entities.Question;
import org.example.utils.FileUtils;
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
            setReader(FileUtils.readFile(path));
        } catch (IOException e) {
            System.out.println(e.toString());
            this.reader = null;
            return;
        }
        setCsvReader(FileUtils.getCsvReader(reader, separator, ignore_quotations));
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

    public List<String> readColumn(String source, String colName) {
        List<String> col = new LinkedList<>();
        Deque<String[]> deque = new LinkedList<>(readRawLines(source));

        Integer orderNum = FileUtils.getColumnOrder(deque.getFirst(), colName);
        if (orderNum==null)
            return null;

        deque.removeFirst();
        for (String[] line: deque){
            col.add(line[orderNum]);
        }
        return col;
    }

    @Override
    public List<Question> getQuestions(String source){
        List<Question> questions = new LinkedList<>();
        List<String> column = readColumn(source, "Question");
        int i = 1;
        for (String row: column){
            questions.add(new Question(i,row));
            i++;
        }
        return questions;
    }

    public List<Question> getQuestionsRel(String source){
        return getQuestions(FileUtils.getResource(source));
    }

}
