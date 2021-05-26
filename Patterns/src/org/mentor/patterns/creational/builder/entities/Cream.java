package org.mentor.patterns.creational.builder.entities;

public class Cream {
    private String cream;

    public Cream(String cream) {
        this.cream = cream;
    }

    @Override
    public String toString() {
        return "Cream{" +
                "cream='" + cream + '\'' +
                '}';
    }
}
