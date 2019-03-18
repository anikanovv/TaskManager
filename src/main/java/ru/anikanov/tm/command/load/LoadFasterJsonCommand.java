package ru.anikanov.tm.command.load;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        File file = new File("faster.json");
        ObjectMapper mapper = new ObjectMapper();
        Project value = mapper.readValue(file, Project.class);
        System.out.println(value);
    }
}
