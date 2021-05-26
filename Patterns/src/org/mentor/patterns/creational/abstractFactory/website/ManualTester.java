package org.mentor.patterns.creational.abstractFactory.website;

import org.mentor.patterns.creational.abstractFactory.Tester;

public class ManualTester implements Tester {
    @Override
    public void testCode() {
        System.out.println("Manual tester tests website");
    }
}
