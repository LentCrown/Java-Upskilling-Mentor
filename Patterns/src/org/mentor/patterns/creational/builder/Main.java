package org.mentor.patterns.creational.builder;

import org.mentor.patterns.creational.builder.entities.Cake;

public class Main {
    public static void main(String[] args) {
        Conveyor conveyor = new Conveyor();
        CakeBuilder cakeBuilder = new CakeBuilder();
        conveyor.createBirthDayCake(cakeBuilder, 15);
        Cake cake = cakeBuilder.bakeCake();
        System.out.println(cake.toString());

        cakeBuilder = new CakeBuilder();
        conveyor.createSimpleCake(cakeBuilder);
        Cake simpleCake = cakeBuilder.bakeCake();
        System.out.println(simpleCake.toString());
    }
}
