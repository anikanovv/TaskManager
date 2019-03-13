package ru.anikanov.tm.command.system;

import com.jcabi.manifests.Manifests;
import ru.anikanov.tm.command.AbstractCommand;
import com.jcabi.*;

import java.util.jar.Manifest;

public class AboutCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "about";
    }

    @Override
    public String getDescription() {
        return "information about";
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public void execute() {
        System.out.println(Manifests.read("Implementation-Version") + " version");
    }
}
