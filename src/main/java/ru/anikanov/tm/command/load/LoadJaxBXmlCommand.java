package ru.anikanov.tm.command.load;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Domain;
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
        return "deserialize all projects and tasks with jaxb";
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public void execute() throws Exception {
        @NotNull final JAXBContext context = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller unmarshaller = context.createUnmarshaller();
        @Nullable final Domain domain = (Domain) unmarshaller.unmarshal(new File(bootstrap.getCurrentUser() + ".xml"));
        System.out.println(domain);
    }
}
