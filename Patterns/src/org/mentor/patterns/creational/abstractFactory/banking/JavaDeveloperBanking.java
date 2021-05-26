package org.mentor.patterns.creational.abstractFactory.banking;

import org.mentor.patterns.creational.abstractFactory.Developer;

public class JavaDeveloperBanking implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Java developer writes java code..");
    }
}
