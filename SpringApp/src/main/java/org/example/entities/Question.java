package org.example.entities;

public class Question {

    private int number;
    private String question;

    Question(int number){
        this.number = number;
    }

    public int getNumber() {return number;}

    public String getQuestion() {return question;}

    public void setNumber(int number) {this.number = number;}

    public void setQuestion(String question) {this.question = question;}

}
