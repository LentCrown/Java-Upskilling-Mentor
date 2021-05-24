package org.mentor.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AnswerTest {
    Answer answer1;
    Answer answer2;
    Answer answer3;
    Answer answer4;

    @Before
    public void init(){
        answer1 = new Answer("Text1");
        answer2 = answer1;
        answer3 = new Answer("Text1");
        answer4 = new Answer("Text2");
    }

    @Test
    public void equalsTestReflexivity(){
        assertThat(answer1.equals(answer1)).isTrue();
    }

    @Test
    public void equalsTestSymmetry(){
        if (answer1.equals(answer3))
            assertThat(answer3.equals(answer1)).isTrue();
    }

    @Test
    public void equalsTestTransitivity(){
        if (answer3.equals(answer1) && answer1.equals(answer2))
            assertThat(answer3.equals(answer2)).isTrue();
    }

    @Test
    public void equalsTestConsistency(){
        if (answer1.equals(answer3))
            assertThat(answer1.equals(answer3)).isTrue();
    }

    @Test
    public void equalsTestNull(){
        assertThat(answer1.equals(null)).isFalse();
    }

    @Test
    public void equalsTestReferences(){
        assertThat(answer1==answer2).isTrue();
        assertThat(answer1==answer3).isFalse();
    }

    @Test
    public void hashcodeTestConsistency(){
        int hashcode = answer1.hashCode();
        assertThat(hashcode).isEqualTo(answer1.hashCode());
    }

    @Test
    public void hashcodeTestIfEqual(){
        if (answer1.equals(answer3))
            assertThat(answer1.hashCode()).isEqualTo(answer3.hashCode());
    }
}