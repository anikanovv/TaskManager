package ru.anikanov.tm.command.save;

import ru.anikanov.tm.command.AbstractCommand;

public class SaveFasterXmlCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "fasterxml json";
    }

    @Override
    public String getDescription() {
        return "serialize all projects and tasks with fasterxml";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        if (bootstrap.getUserEndPoint().checkadmin(bootstrap.getCurrentSession())) {
            bootstrap.getDomainEndPoint().fasterXmlSerialize(bootstrap.getCurrentSession());
        } else System.out.println("Don't have rights");
    }
}
