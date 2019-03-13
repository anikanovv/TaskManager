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
        IProjectService projectService = bootstrap.getProjectService();
        String name = scanner.nextLine();
        System.out.println("Введите через знак ; описание проекта, дату начала проекта, дату окончания проекта");
        String[] projectInfo = scanner.nextLine().split(";");
        String description = null;
        String startDate = null;
        String endDate = null;
        if (projectInfo.length > 2) {
            description = projectInfo[0];
            startDate = projectInfo[1];
            endDate = projectInfo[2];
        }
        projectService.merge(name, description, startDate, endDate, bootstrap.getCurrentUser());
    }
}
