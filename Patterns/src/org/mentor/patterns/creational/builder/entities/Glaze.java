package org.mentor.patterns.creational.builder.entities;

public class Glaze {
    private String glaze;

    public Glaze(String glaze) {
        this.glaze = glaze;
    }

    @Override
    public String toString() {
        return "Glaze{" +
                "glaze='" + glaze + '\'' +
                '}';
    }
}
