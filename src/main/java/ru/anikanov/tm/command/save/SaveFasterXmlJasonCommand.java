package ru.anikanov.tm.command.save;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import java.io.File;

public class SaveFasterXmlJasonCommand extends AbstractCommand {
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
        Project student = new Project("asd", "asd", "12.11.1111", "12.12.1111", bootstrap.getCurrentUser());

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File("fastxsml.xml"), student);
        String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        System.out.println(xml);
    }
}
