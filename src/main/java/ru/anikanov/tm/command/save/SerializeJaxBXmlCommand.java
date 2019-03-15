package ru.anikanov.tm.command.save;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class SerializeJaxBXmlCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "serialize jaxbxml";
    }

    @Override
    public String getDescription() {
        return "serialize all projects and tasks with jax-b";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        Project student = new Project("asd", "asd", "12.11.1111", "12.12.1111", bootstrap.getCurrentUser());
        convertObjectToXml(student);

    }

    private static void convertObjectToXml(Project student) {
        try {
            JAXBContext context = JAXBContext.newInstance(Project.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(student, new File("jaxb.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
