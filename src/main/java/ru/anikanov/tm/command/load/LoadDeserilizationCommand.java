package ru.anikanov.tm.command.load;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Paths;

public class LoadDeserilizationCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "desirilization";
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
        try (@NotNull final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bootstrap.getCurrentUser() + ".dat"))) {
            @Nullable final Project project = (Project) ois.readObject();
            System.out.println(project);
        }
    }
}
