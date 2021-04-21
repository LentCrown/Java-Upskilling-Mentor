package org.example.data.entities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

@Service
@PropertySource("classpath:csv.properties")
public class CSV implements TableFormat {

    @Value(value = "${csv.separator}")
    private char separator;
    @Value(value = "${csv.ignore_quotations}")
    private boolean ignore_quotations;
    private String path = null;
    private Reader reader = null;
    private CSVReader csvReader = null;

    public CSV(){
    }

    public void setPath(String path, boolean inResources){
        if (inResources)
            this.path = FileUtils.getResource(path);
        else
            this.path = path;
    }

    private void setReader(Reader reader) {
        this.reader = reader;
    }

    private void setCsvReader(CSVReader csvReader) { this.csvReader = csvReader; }

    public void openFile() {
        try {
            setReader(FileUtils.readFile(path));
        } catch (IOException e) {
            System.out.println(e.toString());
            this.reader = null;
            return;
        }
        setCsvReader(FileUtils.getCsvReader(reader, separator, ignore_quotations));
    }

    public void closeFile() {
        try {
            this.reader.close();
            this.csvReader.close();
            this.reader = null;
            this.csvReader = null;
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public List<String[]> readRawLines(){
        if (path==null)
            return null;
        openFile();
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
    public List<String> readColumn(String colName) {
        List<String> col = new LinkedList<>();
        Deque<String[]> deque = new LinkedList<>(readRawLines());

        Integer orderNum = FileUtils.getColumnOrder(deque.getFirst(), colName);
        if (orderNum==null)
            System.out.println("No such column");

        deque.removeFirst();
        for (String[] line: deque){
            col.add(line[orderNum]);
        }
        return col;
    }

    @Override
    public String read(){
        StringBuilder data = new StringBuilder();
        List<String[]> lines = readRawLines();
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
}
