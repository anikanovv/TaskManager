package ru.anikanov.tm.command.user;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class UserEndSessionCommand extends AbstractCommand {

    public UserEndSessionCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
    public void execute() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (!isSecure()) return;
        String login = bootstrap.getCurrentUser();
        System.out.println(login);
        bootstrap.setCurrentUser(null);
        System.out.println("Session end");
    }
}
