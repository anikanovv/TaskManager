package ru.anikanov.tm.command.task;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;

import java.text.ParseException;

public class TaskReadCommand extends AbstractCommand {
    public TaskReadCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "read task";
    }

    @Override
    public String getDescription() {
        return "command to read task";
    }
    @Override
    public void execute() throws ParseException {
        if (!isSecure()) return;
        String name = scanner.next();
        System.out.println(bootstrap.taskService.findOne(name));
    }
}
