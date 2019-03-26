package ru.anikanov.tm.command.save;

import ru.anikanov.tm.command.AbstractCommand;

public class SaveJaxBJsonCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "jax b";
    }

    @Override
    public String getDescription() {
        return "serialize all projects and tasks with jax-b";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        if (bootstrap.getUserEndPoint().checkadmin(bootstrap.getCurrentSession())) {
            bootstrap.getDomainEndPoint().jaxbJsonSerialize(bootstrap.getCurrentSession());
        } else System.out.println("Don't have rights");
    }
}
