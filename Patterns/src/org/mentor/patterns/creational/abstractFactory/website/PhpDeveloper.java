package org.mentor.patterns.creational.abstractFactory.website;

import org.mentor.patterns.creational.abstractFactory.Developer;

public class PhpDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("php developer writes php code");
    }
}
