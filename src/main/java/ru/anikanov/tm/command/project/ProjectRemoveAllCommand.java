package ru.anikanov.tm.command.project;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.ProjectServiceInterface;
public class ProjectRemoveAllCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "removeall projects";
    }

    @Override
    public String getDescription() {
        return "command to remove all projects";
    }
    @Override
    public boolean isSecure() {
        return false;
    }

    @Override

    public void execute() {
        ProjectServiceInterface projectService = bootstrap.getProjectService();
        projectService.removeAll(bootstrap.getCurrentUser());
    }
}
