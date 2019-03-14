package ru.anikanov.tm.command.project;

import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.command.AbstractCommand;

public class ProjectUpdateCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "update project";
    }

    @Override
    public String getDescription() {
        return "command to update project";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() {
        final IProjectService projectService = bootstrap.getProjectService();
        final String name = bootstrap.getTerminalService().nextLine();
        System.out.println("Введите через знак ; описание проекта, дату начала проекта, дату окончания проекта");
        final String description = bootstrap.getTerminalService().nextLine();
        ;
        final String startDate = bootstrap.getTerminalService().nextLine();
        ;
        final String endDate = bootstrap.getTerminalService().nextLine();
        ;

        projectService.merge(name, description, startDate, endDate, bootstrap.getCurrentUser());
    }
}
