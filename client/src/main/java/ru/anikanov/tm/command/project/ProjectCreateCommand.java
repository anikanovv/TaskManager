package ru.anikanov.tm.command.project;

import ru.anikanov.tm.command.AbstractCommand;

public class ProjectCreateCommand extends AbstractCommand {

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
       /* final String userId = bootstrap.getCurrentUser();
        final String name = bootstrap.getTerminalService().nextLine();
        final IProjectEndPoint projectEndPoint= bootstrap.getProjectEndPointService().getProjectEndPointPort();
        System.out.println("Введите через знак ; описание проекта, дату начала проекта, дату окончания проекта");
        final String description = bootstrap.getTerminalService().nextLine();
        final String startDate = bootstrap.getTerminalService().nextLine();
        final String endDate = bootstrap.getTerminalService().nextLine();
        projectEndPointService(name, description, startDate, endDate, userId);*/
    }
}
