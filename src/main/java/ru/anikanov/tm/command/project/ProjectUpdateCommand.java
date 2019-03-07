package ru.anikanov.tm.command.project;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;

import java.text.ParseException;

public class ProjectUpdateCommand extends AbstractCommand {
    public ProjectUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute(String name) throws ParseException {
        System.out.println("Введите через знак ; описание проекта, дату начала проекта, дату окончания проекта");
        String[] projectInfo = scanner.next().split(";");
        String description = null;
        String startDate = null;
        String endDate = null;
        if (projectInfo.length > 2) {
            description = projectInfo[0];
            startDate = projectInfo[1];
            endDate = projectInfo[2];
        }
        bootstrap.projectService.merge(name, description, startDate, endDate);
    }
}
