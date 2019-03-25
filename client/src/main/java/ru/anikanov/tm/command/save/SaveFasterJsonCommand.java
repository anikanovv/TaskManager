package ru.anikanov.tm.command.save;

import ru.anikanov.tm.command.AbstractCommand;


public class SaveFasterJsonCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "serialize json";
    }

    @Override
    public String getDescription() {
        return "serialize all projects and tasks with fasterxml";
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public void execute() throws Exception {
        bootstrap.getDomainEndPoint().fasterJsonSerialize(bootstrap.getCurrentSession());
    }
}
