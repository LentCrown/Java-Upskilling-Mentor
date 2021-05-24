package org.mentor.domain;

public class Answer {
    private String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    public String getAnswer(){return answer;}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Answer)) return false;
        Answer compareTo = (Answer) obj;
        return this.answer.equals(compareTo.getAnswer());
    }
}
