package ru.anikanov.tm.command.user;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class UserRemoveAllCommand extends AbstractCommand {
    public UserRemoveAllCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public boolean isSecure() {
        return false;
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
        bootstrap.userService.removeAll();
    }
}
