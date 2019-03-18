package ru.anikanov.tm.command.save;

import org.jetbrains.annotations.NotNull;
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
        @NotNull final JAXBContext context = JAXBContext.newInstance(Project.class);
        @NotNull final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        @NotNull final File file = new File(bootstrap.getCurrentUser() + ".xml");
        @NotNull final List<Project> projects = bootstrap.getProjectService().findAll(bootstrap.getCurrentUser());
        for (Project project : projects) {
            marshaller.marshal(project, file);
        }
    }
}
