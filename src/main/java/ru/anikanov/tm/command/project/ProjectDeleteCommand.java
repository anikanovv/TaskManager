package ru.anikanov.tm.command.project;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.command.AbstractCommand;

public class ProjectDeleteCommand extends AbstractCommand {

    public ProjectDeleteCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

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
        final IProjectService projectService = bootstrap.getProjectService();
        final String name = bootstrap.getTerminlService().nextLine();
        projectService.remove(name, bootstrap.getCurrentUser());
    }
}
