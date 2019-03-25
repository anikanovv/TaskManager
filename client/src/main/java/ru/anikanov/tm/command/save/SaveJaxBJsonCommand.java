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
        return true;
    }

    @Override
    public void execute() throws Exception {
        bootstrap.getDomainEndPoint().jaxbJsonSerialize(bootstrap.getCurrentSession());
    }
}
