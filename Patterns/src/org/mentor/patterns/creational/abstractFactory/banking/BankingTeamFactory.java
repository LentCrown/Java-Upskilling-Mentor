package org.mentor.patterns.creational.abstractFactory.banking;

import org.mentor.patterns.creational.abstractFactory.Developer;
import org.mentor.patterns.creational.abstractFactory.Manager;
import org.mentor.patterns.creational.abstractFactory.ProjectTeamFactory;
import org.mentor.patterns.creational.abstractFactory.Tester;

public class BankingTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new JavaDeveloperBanking();
    }

    @Override
    public Tester getTester() {
        return new QATester();
    }

    @Override
    public Manager getProjectManager() {
        return new BankingProjectManager();
    }
}
