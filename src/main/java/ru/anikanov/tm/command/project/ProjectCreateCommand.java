package ru.anikanov.tm.command.project;

import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.command.AbstractCommand;

import java.text.ParseException;

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
        String userId = bootstrap.getCurrentUser();
        String name = scanner.nextLine();
        IProjectService projectService = bootstrap.getProjectService();
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
        projectService.persist(name, description, startDate, endDate, userId);
    }
}
