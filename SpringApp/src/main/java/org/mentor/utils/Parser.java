package org.mentor.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static CSVReader getCsvReader(Reader reader, char separator, boolean ignore_quotations){
        CSVParser csvParser = new CSVParserBuilder().withSeparator(separator).withIgnoreQuotations(ignore_quotations).build();
        return new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(csvParser).build();
    }

    public static List<String> getColumn(List<String[]> rawData, String columnName){
        boolean first = true;
        int column_index=0;
        List<String> column = new ArrayList<>();
        for (String[] row : rawData){
            while (first) {
                for (int i=0; i<row.length; i++){
                    if (row[i].equals(columnName)){
                        column_index = i;
                        first = false;
                        break;
                    }
                }
            }
            column.add(row[column_index]);
        }
        if (column.size()==0) return null;
        return column;
    }
}
