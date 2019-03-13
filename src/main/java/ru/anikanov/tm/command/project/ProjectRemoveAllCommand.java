package ru.anikanov.tm.command.project;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.command.AbstractCommand;
public class ProjectRemoveAllCommand extends AbstractCommand {

    public ProjectRemoveAllCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

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
        final IProjectService projectService = bootstrap.getProjectService();
        projectService.removeAll(bootstrap.getCurrentUser());
    }
}
