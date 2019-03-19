package ru.anikanov.tm.command.save;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Domain;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Task;

import java.io.FileOutputStream;
import java.util.List;


public class SaveFasterJsonCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "serialize json";
    }

    @Override
    public String getDescription() {
        return "serialize all projects and tasks with fasterxml";
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public void execute() throws Exception {
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        @NotNull final FileOutputStream fos = new FileOutputStream(bootstrap.getCurrentUser() + ".xml");
        @NotNull final List<Project> projects = bootstrap.getProjectService().findAll(bootstrap.getCurrentUser());
        @NotNull final List<Task> tasks = bootstrap.getTaskService().findAll(bootstrap.getCurrentUser());
        @NotNull final Domain domain = new Domain();
        domain.setProjects(projects);
        domain.setTasks(tasks);
        mapper.writerWithDefaultPrettyPrinter().writeValue(fos, domain);
    }
}
