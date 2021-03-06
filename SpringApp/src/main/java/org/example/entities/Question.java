package org.example.entities;

import org.springframework.stereotype.Component;

@Component
public class Question implements Comparable<Question> {

    private Integer id;
    private String title;

    public Question(){
        this.id = null;
        this.title = null;
    }
    public Question(Integer id, String title){
        this.id = id;
        this.title = title;
    }

    public int getId() {return id;}

    public String getTitle() {return title;}

    public boolean equals(Object object){
        if (this==object) return true;
        if (object == null || getClass()!= object.getClass()) return false;
        if (object instanceof Question){
            Question question = (Question) object;
            return (id.equals(question.id)
                    && title.equals(question.title));
        }
        return false;
    }

    @Override
    public int hashCode(){
        final int shift = 31;
        int result = 1;
        result = shift * result + id.hashCode();
        result = shift * result + title.hashCode();
        return result;
    }

    @Override
    public int compareTo(Question question){
        int result = this.id.compareTo(question.id);
        if (result == 0){
            result = this.title.compareTo(question.title);
        }
        return result;
    }

    @Override
    public String toString(){
        return getId() + " " + getTitle();
    }

}
