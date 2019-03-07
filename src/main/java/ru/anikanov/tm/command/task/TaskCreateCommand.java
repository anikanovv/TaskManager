package ru.anikanov.tm.command.task;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;

import java.text.ParseException;

public class TaskCreateCommand extends AbstractCommand {
    public TaskCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "create task";
    }

    @Override
    public String getDescription() {
        return "command to create task";
    }

    @Override
    public void execute(String name) throws ParseException {
        System.out.println("Введите через знак ; описание задачи, дату начала задачи, дату окончания задачи");
        String[] taskInfo = scanner.next().split(";");
        String projectId = null;
        String description = null;
        String startDate = null;
        String endDate = null;
        if (taskInfo.length > 3) {
            projectId = taskInfo[0];
            description = taskInfo[1];
            startDate = taskInfo[2];
            endDate = taskInfo[3];
        }
        bootstrap.taskService.persist(projectId, name, description, startDate, endDate);
    }
}