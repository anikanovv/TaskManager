package ru.anikanov.tm.command.project;

import ru.anikanov.tm.command.AbstractCommand;

public class ProjectFindAllCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "findall";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        bootstrap.getProjectService().findAll(bootstrap.getCurrentUser()).forEach(project -> System.out.println(project));
    }
}
