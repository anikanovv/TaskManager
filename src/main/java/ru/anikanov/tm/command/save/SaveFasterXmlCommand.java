package ru.anikanov.tm.command.save;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import java.io.FileOutputStream;
import java.util.List;

public class SaveFasterXmlCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "fasterxml json";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public void execute() throws Exception {
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        @NotNull final FileOutputStream file = new FileOutputStream(bootstrap.getCurrentUser() + ".xml");
        @NotNull final List<Project> projects = bootstrap.getProjectService().findAll(bootstrap.getCurrentUser());
        for (@Nullable Project project : projects) {
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(file, project);
        }
    }
}
