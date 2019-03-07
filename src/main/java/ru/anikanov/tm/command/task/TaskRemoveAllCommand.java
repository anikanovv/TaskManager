package ru.anikanov.tm.command.task;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;

import java.text.ParseException;

public class TaskRemoveAllCommand extends AbstractCommand {
    public TaskRemoveAllCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "removeall tasks";
    }

    @Override
    public String getDescription() {
        return "command to remove all tasks";
    }
    @Override
    public void execute(String name) throws ParseException {
        bootstrap.taskService.removeAll();
    }
}
