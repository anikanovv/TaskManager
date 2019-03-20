package ru.anikanov.tm.command.project;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.ProjectEndPoint;

public class ProjectReadCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "read project";
    }

    @Override
    public String getDescription() {
        return "command to read project";
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public void execute() {
        final ProjectEndPoint projectEndPoint= bootstrap.getProjectEndPoint();
        final String name = bootstrap.getTerminalService().nextLine();
        System.out.println(projectEndPoint.findProjectByPartOfNameProject(name, bootstrap.getCurrentUser()));
    }
}
