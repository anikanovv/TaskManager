package ru.anikanov.tm.command.load;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.Domain;

import java.io.File;
import java.util.Objects;

public class LoadFasterJsonCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "load fasterjson";
    }

    @Override
    public String getDescription() {
        return "deserialize all projects and tasks with fasterxml";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        if (bootstrap.getUserEndPoint().checkadmin(bootstrap.getCurrentSession())) {
            @NotNull final File file = new File(Objects.requireNonNull(bootstrap.getCurrentSession()).getUserId() + ".json");
            @NotNull final ObjectMapper mapper = new ObjectMapper();
            @Nullable final Domain value = mapper.readValue(file, Domain.class);
            System.out.println(value);
        } else System.out.println("Don't have rights");
    }
}
