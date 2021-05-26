package org.mentor.patterns.creational.singleton;

public class Main {
    public static void main(String[] args) {
        Man man1 = Man.getInstance("Vladislav", "Glebov", "Petrovich", "2020-01-01");
        Man man2 = Man.getInstance("", "", "", "");
        System.out.println(man1 == man2);
        System.out.println(man1.hashCode());
        System.out.println(man2.hashCode());
        System.out.println(man1.toString());
        System.out.println(man1.toString());
    }
}
