package ru.anikanov.tm.command.save;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Domain;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Task;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.util.List;

public class SaveJaxBJsonCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "jax b";
    }

    @Override
    public String getDescription() {
        return "serialize all projects and tasks with jax-b";
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public void execute() throws Exception {
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("eclipselink.media-type", "application/json");
        @NotNull final FileWriter file = new FileWriter(/*bootstrap.getCurrentUser() + */"man.json");
        @NotNull final List<Project> projects = bootstrap.getProjectService().findAll(bootstrap.getCurrentUser());
        @NotNull final List<Task> tasks = bootstrap.getTaskService().findAll(bootstrap.getCurrentUser());
        @NotNull final Domain domain = new Domain();
        domain.setProjects(projects);
        domain.setTasks(tasks);
        marshaller.marshal(domain, file);
    }
}
