package ru.anikanov.tm.command.save;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class SaveJaxBXmlCommand extends AbstractCommand {
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
        return true;
    }

    @Override
    public void execute() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Project.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("fastxsml.xml");
        List<Project> projects = bootstrap.getProjectService().findAll(bootstrap.getCurrentUser());
        for (Project project : projects) {
            marshaller.marshal(project, file);
        }
    }
}
