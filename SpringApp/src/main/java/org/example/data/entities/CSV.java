package org.example.data.entities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.utils.FileUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSV implements TableFormat {

    public static char separator = ';';
    public static boolean ignore_quotations = true;

    private String path = null;
    private Reader reader = null;
    private CSVReader csvReader = null;

    public CSV(String path){
        setPath(path);
        open();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setCsvReader(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    public Reader getReader(){
        return reader;
    }

    public CSVReader getCsvReader(){
        return csvReader;
    }

    @Override
    public void open() {
        try {
            setReader(FileUtils.readFile(path));
        } catch (IOException e) {
            System.out.println(e.toString());
            this.reader = null;
            return;
        }
        setCsvReader(FileUtils.getCsvReader(reader, separator, ignore_quotations));
    }

    @Override
    public void close() {
        try {
            this.reader.close();
            this.csvReader.close();
            this.reader = null;
            this.csvReader = null;
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void reOpen(){
        close();
        open();
    }

    public List<String[]> readRawLines(){
        List<String[]> lines = new ArrayList<>();
        try {
            lines = this.csvReader.readAll();
        } catch (IOException | CsvException e) {
            System.out.println(e);
        }
        return lines;
    }

    @Override
    public String read(){
        StringBuilder data = new StringBuilder();
        List<String[]> lines = readRawLines();
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

    /**
     * @param separator Sets the delimiter to use for separating entries.
     * @param ignore_quatations Sets the ignore quotations mode - if true, quotations are ignored.
     */
    public static void parseSettings(char separator, boolean ignore_quatations) {
        CSV.separator = separator;
        CSV.ignore_quotations = ignore_quatations;
    }

}
