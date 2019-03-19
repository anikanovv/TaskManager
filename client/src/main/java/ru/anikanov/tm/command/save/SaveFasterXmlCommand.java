package ru.anikanov.tm.command.save;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Domain;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Task;

import java.io.FileOutputStream;
import java.util.List;

public class SaveFasterXmlCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "fasterxml json";
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
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        @NotNull final FileOutputStream file = new FileOutputStream(bootstrap.getCurrentUser() + "fasterxml" + ".xml");
        @NotNull final List<Project> projects = bootstrap.getProjectService().findAll(bootstrap.getCurrentUser());
        @NotNull final List<Task> tasks = bootstrap.getTaskService().findAll(bootstrap.getCurrentUser());
        @NotNull final Domain domain = new Domain();
        domain.setProjects(projects);
        domain.setTasks(tasks);
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(file, domain);
    }
}
