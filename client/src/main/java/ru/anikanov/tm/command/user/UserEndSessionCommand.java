package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;

public class UserEndSessionCommand extends AbstractCommand {

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public String getName() {
        return "endsession user";
    }

    @Override
    public String getDescription() {
        return "to end session of user";
    }

    @Override
    public void execute() {
        final String login = bootstrap.getCurrentUser();
        System.out.println(login);
        bootstrap.setCurrentUser(null);
        System.out.println("Session end");
    }
}
