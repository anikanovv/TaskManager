package ru.anikanov.tm.command.save;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.file.Paths;

public class SaveJaxBJsonCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "jax b";
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
        JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // To format JSON
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //Set JSON type
        jaxbMarshaller.setProperty("eclipselink.media-type", "application/json");
        jaxbMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);

        //Store JSON to File
        File file = new File("employee.json");
        jaxbMarshaller.marshal(student, file);
    }
}
