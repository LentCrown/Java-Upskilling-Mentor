package org.mentor.patterns.creational.abstractFactory.website;

import org.mentor.patterns.creational.abstractFactory.Manager;

public class WebsiteProjectManager implements Manager {
    @Override
    public void manageProject() {
        System.out.println("website project manager manages website project..");
    }
}
