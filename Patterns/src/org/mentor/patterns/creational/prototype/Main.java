package org.mentor.patterns.creational.prototype;

public class Main {
    public static void main(String[] args) {
        Copyable animal1 = new Animal("HomoSapiens");
        Copyable animal2 = (Animal) animal1.clone();
        Copyable wolf1 = new Wolf("dogs",12);
        Copyable wolf2 = (Animal) wolf1.clone();

        System.out.println(animal1 == animal2);
        System.out.println(animal1.equals(animal2));
        System.out.println(animal1.hashCode());
        System.out.println(animal2.hashCode());
        System.out.println();
        System.out.println(wolf1 == wolf2);
        System.out.println(wolf1.equals(wolf2));
        System.out.println(wolf1.hashCode());
        System.out.println(wolf2.hashCode());
    }
}
