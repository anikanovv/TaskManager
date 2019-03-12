package ru.anikanov.tm.command.project;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.ProjectServiceInterface;
import ru.anikanov.tm.service.TaskService;

import java.text.ParseException;
import java.util.UUID;

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
    public void execute() throws ParseException {
        String userId = bootstrap.getCurrentUser();
        String name = scanner.nextLine();
        ProjectServiceInterface projectService = bootstrap.getProjectService();
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
