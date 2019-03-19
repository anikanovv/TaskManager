package ru.anikanov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Task;

public class TaskFindByPartOfDescriptionCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "findbydescription task";
    }

    @Override
    public String getDescription() {
        return "find task by part of description";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("type description(part of description)");
        @Nullable final String partOfDescription = bootstrap.getTerminalService().nextLine();
        @Nullable Task task = bootstrap.getTaskService().findByPartOfDescription(partOfDescription, bootstrap.getCurrentUser());
        System.out.println(task);
    }
}