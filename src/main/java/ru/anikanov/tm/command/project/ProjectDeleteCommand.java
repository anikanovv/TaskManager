package ru.anikanov.tm.command.project;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import java.text.ParseException;

public class ProjectDeleteCommand extends AbstractCommand {
    public ProjectDeleteCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "delete project";
    }

    @Override
    public String getDescription() {
        return "command to delete project";
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public void execute() throws ParseException {
        String name = scanner.next();
        bootstrap.projectService.remove(name, bootstrap.getCurrentUser());
    }
}
