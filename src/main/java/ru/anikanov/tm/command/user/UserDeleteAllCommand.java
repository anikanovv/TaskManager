package ru.anikanov.tm.command.user;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class UserDeleteAllCommand extends AbstractCommand {
    public UserDeleteAllCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "removeall users";
    }

    @Override
    public String getDescription() {
        return "to remove all users";
    }

    @Override
    public void execute() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (!isSecure()) return;
        bootstrap.userService.removeAll();
    }
}
