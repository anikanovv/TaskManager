package ru.anikanov.tm.command.load;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Domain;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Objects;

public class LoadDeserilizationCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "deserialization";
    }

    @Override
    public String getDescription() {
        return "deserialize all projects and tasks";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        if (bootstrap.getUserEndPoint().checkadmin(bootstrap.getCurrentSession())) {
            try (@NotNull final ObjectInputStream ois = new ObjectInputStream(new FileInputStream((Objects.requireNonNull(bootstrap.getCurrentSession()).getUserId()) + ".dat"))) {
                @Nullable final Domain domain = (Domain) ois.readObject();
                System.out.println(domain);
            }
        } else System.out.println("Don't have rights");
    }
}
