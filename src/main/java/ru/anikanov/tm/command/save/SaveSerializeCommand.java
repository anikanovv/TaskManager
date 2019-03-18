package ru.anikanov.tm.command.save;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SaveSerializeCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "serialize";
    }

    @Override
    public String getDescription() {
        return "serialize all projects and tasks";
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public void execute() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.dat"));
        List<Project> projects = bootstrap.getProjectService().findAll(bootstrap.getCurrentUser());
        for (Project project : projects) {
            oos.writeObject(project);
        }
    }
}
