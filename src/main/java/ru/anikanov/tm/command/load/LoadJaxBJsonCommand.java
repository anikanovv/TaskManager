package ru.anikanov.tm.command.load;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class LoadJaxBJsonCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "load jaxbjson";
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
        JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
        Unmarshaller u = jaxbContext.createUnmarshaller();
        u.setProperty("eclipselink.media-type", "application/json");
        File file = new File("faster.json");
        Project project = (Project) u.unmarshal(file);
        project.getName();
        System.out.println(project);
    }
}
