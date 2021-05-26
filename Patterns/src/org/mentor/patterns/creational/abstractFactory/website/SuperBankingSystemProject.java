package org.mentor.patterns.creational.abstractFactory.website;

import org.mentor.patterns.creational.abstractFactory.Developer;
import org.mentor.patterns.creational.abstractFactory.Manager;
import org.mentor.patterns.creational.abstractFactory.ProjectTeamFactory;
import org.mentor.patterns.creational.abstractFactory.Tester;
import org.mentor.patterns.creational.abstractFactory.banking.BankingTeamFactory;

public class SuperBankingSystemProject {
    public static void main(String[] args) {
        ProjectTeamFactory projectTeamFactory = new BankingTeamFactory();
        Developer developer = projectTeamFactory.getDeveloper();
        Tester tester = projectTeamFactory.getTester();
        Manager manager = projectTeamFactory.getProjectManager();

        System.out.println("Creating banking system..");
        developer.writeCode();
        tester.testCode();
        manager.manageProject();
    }
}
