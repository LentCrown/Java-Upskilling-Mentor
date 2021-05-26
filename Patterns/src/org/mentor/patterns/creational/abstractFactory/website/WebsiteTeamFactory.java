package org.mentor.patterns.creational.abstractFactory.website;

import org.mentor.patterns.creational.abstractFactory.Developer;
import org.mentor.patterns.creational.abstractFactory.Manager;
import org.mentor.patterns.creational.abstractFactory.ProjectTeamFactory;
import org.mentor.patterns.creational.abstractFactory.Tester;

public class WebsiteTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new PhpDeveloper();
    }

    @Override
    public Tester getTester() {
        return new ManualTester();
    }

    @Override
    public Manager getProjectManager() {
        return new WebsiteProjectManager();
    }
}
