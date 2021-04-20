package org.example.data.entities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public CSV(String rel_path) throws URISyntaxException {
        String abs_path = Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                .getResource(rel_path)).toURI()).toString();
        setPath(abs_path);
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

    @Override
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
        openFile();
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
        closeFile();
        return data.toString();
    }
}
