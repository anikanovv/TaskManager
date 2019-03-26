package ru.anikanov.tm.command.load;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Domain;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Objects;

public class LoadJaxBJsonCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "load jaxbjson";
    }

    @Override
    public String getDescription() {
        return "deserialize all projects and tasks with jaxb";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        if (bootstrap.getUserEndPoint().checkadmin(bootstrap.getCurrentSession())) {
            @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
            @NotNull final Unmarshaller u = jaxbContext.createUnmarshaller();
            u.setProperty("eclipselink.media-type", "application/json");
            @NotNull final File file = new File(Objects.requireNonNull(bootstrap.getCurrentSession()).getUserId() + ".json");
            @Nullable final Domain domain = (Domain) u.unmarshal(file);
            System.out.println(domain);
        } else System.out.println("Don't have rights");
    }
}
