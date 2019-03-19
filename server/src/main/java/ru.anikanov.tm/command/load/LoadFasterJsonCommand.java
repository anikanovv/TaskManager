package ru.anikanov.tm.command.load;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Domain;
import java.io.File;

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
        return true;
    }

    @Override
    public void execute() throws Exception {
        @NotNull final File file = new File(bootstrap.getCurrentUser() + ".json");
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        @Nullable final Domain value = mapper.readValue(file, Domain.class);
        System.out.println(value);
    }
}
