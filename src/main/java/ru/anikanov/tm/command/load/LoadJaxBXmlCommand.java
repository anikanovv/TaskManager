package ru.anikanov.tm.command.load;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class LoadJaxBXmlCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "load jaxbxml";
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
        JAXBContext context = JAXBContext.newInstance(Project.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Project project = (Project) unmarshaller.unmarshal(new File("jaxb.xml"));
        System.out.println(project);
    }
}
