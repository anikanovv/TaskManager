package ru.anikanov.tm.command.save;

import ru.anikanov.tm.command.AbstractCommand;

public class SaveSerializeCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "serialize";
    }

    @Override
    public String getDescription() {
        return "basic serialize all projects and tasks";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        if (bootstrap.getUserEndPoint().checkadmin(bootstrap.getCurrentSession())) {
            bootstrap.getDomainEndPoint().standartSerialize(bootstrap.getCurrentSession());
        } else System.out.println("Don't have rights");
    }
}
