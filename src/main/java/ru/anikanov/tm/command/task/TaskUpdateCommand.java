package ru.anikanov.tm.command.task;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;

import java.text.ParseException;

public class TaskUpdateCommand extends AbstractCommand {
    public TaskUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "update task";
    }

    @Override
    public String getDescription() {
        return "command to update task";
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public void execute() throws ParseException {
        String id = scanner.next();
        String name = scanner.next();
        System.out.println("Введите через знак ; описание задачи, дату начала задачи, дату окончания задачи");
        String[] taskInfo = scanner.next().split(";");
        String description = null;
        String startDate = null;
        String endDate = null;
        if (taskInfo.length > 2) {
            description = taskInfo[0];
            startDate = taskInfo[1];
            endDate = taskInfo[2];
        }
        bootstrap.taskService.merge(id, name, description, startDate, endDate, bootstrap.getCurrentUser());
    }
}
