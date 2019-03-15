package ru.anikanov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Task;

public class TaskFindByPartOfNameCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "findbypart task";
    }

    @Override
    public String getDescription() {
        return "find task by part of name";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("type name(part of description)");
        @Nullable final String partOfDescription = bootstrap.getTerminalService().nextLine();
        @Nullable Task task = bootstrap.getTaskService().findByPartOfName(partOfDescription, bootstrap.getCurrentUser());
        System.out.println(task);
    }
}
