package ru.anikanov.tm.command.project;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.command.AbstractCommand;

public class ProjectCreateCommand extends AbstractCommand {
    public ProjectCreateCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String getName() {
        return "create project";
    }

    @Override
    public String getDescription() {
        return "command to create project";
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public void execute() {
        final String userId = bootstrap.getCurrentUser();
        final String name = bootstrap.getTerminlService().nextLine();
        final IProjectService projectService = bootstrap.getProjectService();
        System.out.println("Введите через знак ; описание проекта, дату начала проекта, дату окончания проекта");
        final String description = bootstrap.getTerminlService().nextLine();
        final String startDate = bootstrap.getTerminlService().nextLine();
        final String endDate = bootstrap.getTerminlService().nextLine();
        projectService.persist(name, description, startDate, endDate, userId);
    }
}
