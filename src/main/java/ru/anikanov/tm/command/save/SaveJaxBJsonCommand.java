package ru.anikanov.tm.command.save;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

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
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
        @NotNull final Marshaller m = jaxbContext.createMarshaller();
        m.setProperty("eclipselink.media-type", "application/json");
        @NotNull final File file = new File(bootstrap.getCurrentUser() + ".xml");
        @NotNull final List<Project> projects = bootstrap.getProjectService().findAll(bootstrap.getCurrentUser());
        for (Project project : projects) {
            m.marshal(project, file);
        }

    }
}
