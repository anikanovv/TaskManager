package ru.anikanov.tm.command.project;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.ProjectEndPoint;

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
        final ProjectEndPoint projectEndPoint= bootstrap.getProjectEndPoint();
        projectEndPoint.findAllProject(bootstrap.getCurrentUser()).forEach(project -> System.out.println(project));
    }
}
