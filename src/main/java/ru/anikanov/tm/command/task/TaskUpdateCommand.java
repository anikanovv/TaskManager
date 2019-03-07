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
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute(String name) throws ParseException {
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
        bootstrap.taskService.merge(name, description, startDate, endDate);
    }
}
