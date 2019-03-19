package ru.anikanov.tm.command.save;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Domain;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Task;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SaveSerializeCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "serialize";
    }

    @Override
    public String getDescription() {
        return "basic serialize all projects and tasks";
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public void execute() throws Exception {
        @NotNull final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bootstrap.getCurrentUser() + ".dat"));
        @NotNull final List<Project> projects = bootstrap.getProjectService().findAll(bootstrap.getCurrentUser());
        @NotNull final List<Task> tasks = bootstrap.getTaskService().findAll(bootstrap.getCurrentUser());
        @NotNull final Domain domain = new Domain();
        domain.setProjects(projects);
        domain.setTasks(tasks);
        oos.writeObject(domain);
    }
}
