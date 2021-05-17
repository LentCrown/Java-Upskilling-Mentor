package org.mentor.service;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mentor.config.CSVConfigTest;
import org.mentor.config.ReportConfigTest;
import org.mentor.config.SourcePathConfigTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties({SourcePathConfigTest.class, ReportConfigTest.class, CSVConfigTest.class})
class ApplicationTest {
    private final String SOURCE = "Test.csv";
    private final float PASS_BORDER = 100;
    private final char SEPARATOR = '+';

    @Autowired
    private SourcePathConfigTest sourcePathConfigTest;
    @Autowired
    private ReportConfigTest reportConfigTest;
    @Autowired
    private CSVConfigTest csvConfigTest;

    @Test
    void testSourcePathConfigRead(){
        String source = sourcePathConfigTest.getSource();
        Assertions.assertEquals(SOURCE,source, "Error occurred while trying to read source");
        System.out.println("source=" + source);
    }

    @Test
    void testReportConfigRead(){
        float pass_border = reportConfigTest.getPass_border();
        Assertions.assertEquals(PASS_BORDER,pass_border);
        System.out.println("pass_border=" + pass_border);
    }

    @Test
    void testCSVConfigRead(){
        char separator = csvConfigTest.getSeparator();
        boolean ignore_quotations = csvConfigTest.isIgnore_quotations();
        Assertions.assertEquals(SEPARATOR,separator);
        Assertions.assertTrue(ignore_quotations);
        System.out.println("separator=" + separator);
        System.out.println("ignore_quotations=" + ignore_quotations);
    }

}