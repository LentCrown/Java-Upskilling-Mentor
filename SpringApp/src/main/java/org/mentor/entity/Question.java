package org.mentor.entity;

public class Question {
    private final Integer id;
    private final String title;

    public Question(Integer id, String title){
        this.id = id;
        this.title = title;
    }

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
    public String toString(){
        return "Вопрос " + (id + 1) + " : " + title;
    }
}
