package ru.anikanov.tm.command;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;

import java.text.ParseException;
import java.util.Scanner;

public abstract class AbstractCommand {
    protected Bootstrap bootstrap;
    protected Scanner scanner = new Scanner(System.in);

    public AbstractCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }


    public abstract String getName();

    public abstract String getDescription();

    public abstract void execute() throws ParseException;
}
