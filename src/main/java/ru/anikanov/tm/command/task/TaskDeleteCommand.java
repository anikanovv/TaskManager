package ru.anikanov.tm.command.task;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;

import java.text.ParseException;

public class TaskDeleteCommand extends AbstractCommand {
    public TaskDeleteCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "delete task";
    }

    @Override
    public String getDescription() {
        return "command to delete task";
    }

    @Override
    public void execute(String name) throws ParseException {
        bootstrap.taskService.remove(name);
    }
}
