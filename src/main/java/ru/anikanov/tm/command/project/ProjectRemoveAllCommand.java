package ru.anikanov.tm.command.project;

import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.command.AbstractCommand;
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
        IProjectService projectService = bootstrap.getProjectService();
        projectService.removeAll(bootstrap.getCurrentUser());
    }
}
