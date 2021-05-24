package org.mentor.domain;

import java.util.Objects;

public class Answer {
    private String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    public String getAnswer(){return answer;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer1 = (Answer) o;

        return answer.equals(answer1.answer);
    }

    @Override
    public int hashCode() {
        return answer.hashCode();
    }

    @Override
    public String toString() {
        return answer;
    }
}
