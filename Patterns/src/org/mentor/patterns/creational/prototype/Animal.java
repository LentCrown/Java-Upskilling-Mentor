package org.mentor.patterns.creational.prototype;

public class Animal implements Copyable {
    String familyName;

    public Animal(String familyName) {
        this.familyName = familyName;
    }

    public Animal(Animal toClone) {
        this.familyName = toClone.familyName;
    }

    @Override
    public Object clone() {
        return new Animal(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return familyName.equals(animal.familyName);
    }

    @Override
    public int hashCode() {
        return familyName.hashCode();
    }
}
