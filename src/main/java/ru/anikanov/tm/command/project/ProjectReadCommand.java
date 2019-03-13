package ru.anikanov.tm.command.project;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.command.AbstractCommand;

public class ProjectReadCommand extends AbstractCommand {

    public ProjectReadCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

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
        final IProjectService projectService = bootstrap.getProjectService();
        final String name = bootstrap.getTerminlService().nextLine();
        System.out.println(projectService.findOne(name, bootstrap.getCurrentUser()));
    }
}
