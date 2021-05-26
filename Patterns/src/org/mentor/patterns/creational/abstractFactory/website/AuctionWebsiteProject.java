package org.mentor.patterns.creational.abstractFactory.website;

import org.mentor.patterns.creational.abstractFactory.Developer;
import org.mentor.patterns.creational.abstractFactory.Manager;
import org.mentor.patterns.creational.abstractFactory.ProjectTeamFactory;
import org.mentor.patterns.creational.abstractFactory.Tester;

public class AuctionWebsiteProject {
    public static void main(String[] args) {
        ProjectTeamFactory projectTeamFactory = new WebsiteTeamFactory();
        Developer developer = projectTeamFactory.getDeveloper();
        Tester tester = projectTeamFactory.getTester();
        Manager manager = projectTeamFactory.getProjectManager();

        System.out.println("Creating website..");
        developer.writeCode();
        tester.testCode();
        manager.manageProject();
    }
}
