package ru.anikanov.tm.command.user;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;

import java.text.ParseException;

public class AuthUserCommand extends AbstractCommand {
    public AuthUserCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute(String name) throws ParseException {

    }
}
