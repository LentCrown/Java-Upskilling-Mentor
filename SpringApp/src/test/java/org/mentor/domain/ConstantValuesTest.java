package org.mentor.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mentor.domain.ConstantValues.*;

@RunWith(MockitoJUnitRunner.class)
public class ConstantValuesTest {

    @Test
    public void ConstantValues_RegexYes(){
        String[] inputRowSuccess = {"YES", "yes", "Yes", "yES", "Y"};
        String[] inputRowFailure = {"YE", "ys", "YS"};
        assertThat(Arrays.stream(inputRowSuccess).allMatch(i -> i.matches(REGEX_YES))).isTrue();
        assertThat(Arrays.stream(inputRowFailure).allMatch(i -> i.matches(REGEX_YES))).isFalse();
    }
    @Test
    public void ConstantValues_RegexNo(){
        String[] inputRowSuccess = {"No", "NO", "N", "n"};
        String[] inputRowFailure = {"Not", "NOO", "NoO"};
        assertThat(Arrays.stream(inputRowSuccess).allMatch(i -> i.matches(REGEX_NO))).isTrue();
        assertThat(Arrays.stream(inputRowFailure).allMatch(i -> i.matches(REGEX_NO))).isFalse();
    }
    @Test
    public void ConstantValues_RegexQuit(){
        String[] inputRowSuccess = {"q", "Q", "QUiT", "quIT"};
        String[] inputRowFailure = {"Qui", "QUi", "qU"};
        assertThat(Arrays.stream(inputRowSuccess).allMatch(i -> i.matches(REGEX_QUIT))).isTrue();
        assertThat(Arrays.stream(inputRowFailure).allMatch(i -> i.matches(REGEX_QUIT))).isFalse();
    }
    @Test
    public void ConstantValues_RegexWords(){
        String[] inputRowSuccess = {"Hello", "my", "name", "is", "Anton"};
        String[] inputRowFailure = {"\t", "\n", " ", ";", "Hello my name is Anton", "Text example here", "This is just sample text"};
        assertThat(Arrays.stream(inputRowSuccess).allMatch(i -> i.matches(REGEX_WORDS))).isTrue();
        assertThat(Arrays.stream(inputRowFailure).allMatch(i -> i.matches(REGEX_WORDS))).isFalse();
    }
    @Test
    public void ConstantValues_RegexQuestions(){
        String[] inputRowSuccess = {"Hello", "my", "name", "is", "Anton", "111", "2021", "Text example here", "\n"};
        String[] inputRowFailure = {"\t",";"};
        assertThat(Arrays.stream(inputRowSuccess).allMatch(i -> i.matches(REGEX_QUESTIONS))).isTrue();
        assertThat(Arrays.stream(inputRowFailure).allMatch(i -> i.matches(REGEX_QUESTIONS))).isFalse();
    }
    @Test
    public void ConstantValues_RegexQuestionsWithChoice(){
        String[] inputRowSuccess = {"1", "2", "3", "4", "\n"};
        String[] inputRowFailure = {"\t",";","Hello", "my", "name", "is", "Anton", "111", "2021", "Text example here"};
        assertThat(Arrays.stream(inputRowSuccess).allMatch(i -> i.matches(REGEX_QUESTIONS_WITH_CHOICE))).isTrue();
        assertThat(Arrays.stream(inputRowFailure).allMatch(i -> i.matches(REGEX_QUESTIONS_WITH_CHOICE))).isFalse();
    }
}