package ru.anikanov.tm.command.save;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import java.io.FileOutputStream;
import java.util.List;


public class SaveFasterJsonCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "serialize json";
    }

    @Override
    public String getDescription() {
        return "serialize all projects and tasks with json";
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
        for (Project project : projects) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(fos, project);
        }
    }
}
