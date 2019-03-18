package ru.anikanov.tm.command.load;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import java.io.File;

public class LoadFasterJsonCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "load fasterjson";
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
        @NotNull final File file = new File(bootstrap.getCurrentUser() + ".json");
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        @Nullable final Project value = mapper.readValue(file, Project.class);
        System.out.println(value);
    }
}
