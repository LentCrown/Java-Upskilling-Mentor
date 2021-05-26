package org.mentor.patterns.creational.builder.entities;

public class Flavour {
    private String flavour;

    public Flavour(String flavour) {
        this.flavour = flavour;
    }

    @Override
    public String toString() {
        return "Flavour{" +
                "flavour='" + flavour + '\'' +
                '}';
    }
}
