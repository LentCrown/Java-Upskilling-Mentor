package org.example.entities;

import org.springframework.stereotype.Component;

@Component
public class Question implements Comparable<Question> {

    private Integer number;
    private String title;

    public Question(){
        this.number = null;
        this.title = null;
    }
    public Question(Integer number, String title){
        this.number = number;
        this.title = title;
    }

    public int getNumber() {return number;}

    public String getTitle() {return title;}

    public void setNumber(int number) {this.number = number;}

    public void setTitle(String title) {this.title = title;}

    public boolean equals(Object object){
        if (this==object) return true;
        if (object == null || getClass()!= object.getClass()) return false;
        if (object instanceof Question){
            Question question = (Question) object;
            return (number.equals(question.number)
                    && title.equals(question.title));
        }
        return false;
    }

    @Override
    public int hashCode(){
        final int shift = 31;
        int result = 1;
        result = shift * result + number.hashCode();
        result = shift * result + title.hashCode();
        return result;
    }

    @Override
    public int compareTo(Question question){
        int result = this.number.compareTo(question.number);
        if (result == 0){
            result = this.title.compareTo(question.title);
        }
        return result;
    }

    @Override
    public String toString(){
        return getNumber() + " " + getTitle();
    }

}
