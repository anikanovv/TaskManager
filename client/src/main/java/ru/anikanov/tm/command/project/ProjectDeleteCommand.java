package ru.anikanov.tm.command.project;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.ProjectEndPoint;

public class ProjectDeleteCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "delete project";
    }

    @Override
    public String getDescription() {
        return "command to delete project";
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public void execute() {
        final ProjectEndPoint projectEndPoint= bootstrap.getProjectEndPoint();
        final String name = bootstrap.getTerminalService().nextLine();
        projectEndPoint.removeProject(name, bootstrap.getCurrentSession());
    }
}
