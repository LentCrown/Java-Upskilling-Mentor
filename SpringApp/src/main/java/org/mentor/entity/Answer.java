package org.mentor.entity;

public class Answer {
    private final Integer id;
    private String answer;
    private final String right_answer;

    public Answer(Integer id, String right_answer) {
        this.id = id;
        this.answer = null;
        this.right_answer = right_answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRight_answer() {
        return right_answer;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Answer{" + "id=" + id +", answer='" + answer + '\'' +", right_answer='" + right_answer + '\'' +'}';
    }
}
