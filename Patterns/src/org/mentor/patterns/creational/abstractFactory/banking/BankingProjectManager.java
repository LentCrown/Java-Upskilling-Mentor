package org.mentor.patterns.creational.abstractFactory.banking;

import org.mentor.patterns.creational.abstractFactory.Manager;

public class BankingProjectManager implements Manager {
    @Override
    public void manageProject() {
        System.out.println("Banking PM manages banking project");
    }
}
