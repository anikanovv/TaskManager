package ru.anikanov.tm.command.save;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import java.io.File;
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
        XmlMapper xmlMapper = new XmlMapper();
        FileOutputStream file = new FileOutputStream("fastxsml.xml");
        List<Project> projects = bootstrap.getProjectService().findAll(bootstrap.getCurrentUser());
        for (Project project : projects) {
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(file, project);
        }
    }
}
