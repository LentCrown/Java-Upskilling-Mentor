package org.mentor.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mentor.config.CSVConfig;
import org.mentor.domain.Question;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CsvFileParserTest {
    @Mock private ICsvReader csvFileReader;

    @InjectMocks private CsvFileParser csvFileParser;

    @Test()
    public void parseCsvFileCorrect() {
        List<String[]> parsedCsv = Arrays.asList(
                new String[]{"Сколько океанов на Земле","1","2","3","4","4"},
                new String[]{"Какой океан самый большой?","","","","","Тихий"},
                new String[]{"В каком из пяти океанов самая низкая температура воды?","","","","","Северный Ледовитый"}
        );
        BDDMockito.given(csvFileReader.readRawLines("parsedCsv")).willReturn(parsedCsv);

        List<Question> questionList = csvFileParser.findAll("parsedCsv");
        assertThat(questionList).isNotNull();
        assertThat(questionList.size()).isEqualTo(3);
        assertThat(questionList.get(0).getChoiceList().size()).isEqualTo(4);
        assertThat(questionList.get(0).getChoiceList().get(0).getAnswer()).isEqualTo("1");
        assertThat(questionList.get(0).getCorrect_answer().toString()).isEqualTo("4");
    }

    @Test()
    public void parseCsvFileMissedWholeColumn() {
        List<String[]> parsedCsvMissedWholeColumn = Arrays.asList(
                new String[]{"Сколько океанов на Земле","1","2","3","4"},
                new String[]{"Какой океан самый большой?","","","",""},
                new String[]{"В каком из пяти океанов самая низкая температура воды?","","","",""}
        );
        BDDMockito.given(csvFileReader.readRawLines("parsedCsvMissedWholeColumn")).willReturn(parsedCsvMissedWholeColumn);

        List<Question> questionList = csvFileParser.findAll("parsedCsvMissedWholeColumn");
        assertThat(questionList).isNull();
    }

    @Test()
    public void parseCsvFileMissedColumnValue() {
        List<String[]> parsedCsvMissed1 = Arrays.asList(
                new String[]{"Сколько океанов на Земле","3","4","4"},
                new String[]{"Какой океан самый большой?","","","","","Тихий"},
                new String[]{"В каком из пяти океанов самая низкая температура воды?","","","","","Северный Ледовитый"}
        );
        List<String[]> parsedCsvMissed2 = Arrays.asList(
                new String[]{"Сколько океанов на Земле","1","2","3","4","4"},
                new String[]{"Какой океан самый большой?","","","",""},
                new String[]{"В каком из пяти океанов самая низкая температура воды?","","","","","Северный Ледовитый"}
        );
        BDDMockito.given(csvFileReader.readRawLines("parsedCsvMissed1")).willReturn(parsedCsvMissed1);
        BDDMockito.given(csvFileReader.readRawLines("parsedCsvMissed2")).willReturn(parsedCsvMissed2);

        List<Question> questionList1 = csvFileParser.findAll("parsedCsvMissed1");
        assertThat(questionList1).isNull();

        List<Question> questionList2 = csvFileParser.findAll("parsedCsvMissed2");
        assertThat(questionList2).isNull();
    }

    @Test()
    public void parseTextFile() {
        List<String[]> parsedText = Arrays.asList(
                new String[]{""},
                new String[]{"Suspendisse sit amet blandit risus, a facilisis elit."},
                new String[]{""},
                new String[]{"Morbi est nulla, consectetur sed sapien sed, sagittis sodales ex."}
        );
        BDDMockito.given(csvFileReader.readRawLines("parsedText")).willReturn(parsedText);

        List<Question> questionList = csvFileParser.findAll("parsedText");
        assertThat(questionList).isNull();
    }

    @Test()
    public void parsedNull() {
        BDDMockito.given(csvFileReader.readRawLines("parsedNull")).willReturn(null);

        List<Question> questionList = csvFileParser.findAll("parsedNull");
        assertThat(questionList).isNull();
    }
}