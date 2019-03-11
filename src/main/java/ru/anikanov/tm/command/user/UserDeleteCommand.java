package ru.anikanov.tm.command.user;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class UserDeleteCommand extends AbstractCommand {
    public UserDeleteCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public String getName() {
        return "delete user";
    }

    @Override
    public String getDescription() {
        return "delete one user";
    }

    @Override
    public void execute() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String login = scanner.next();
        bootstrap.userService.remove(login);
    }
}
