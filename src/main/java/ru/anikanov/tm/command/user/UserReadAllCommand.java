package ru.anikanov.tm.command.user;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

public class UserReadAllCommand extends AbstractCommand {
    public UserReadAllCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "readall user";
    }

    @Override
    public String getDescription() {
        return "read info about all users";
    }

    @Override
    public void execute() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (!isSecure()) return;
        List<User> users = bootstrap.userService.findAll();
        users.forEach(user -> System.out.println(user));
    }
}
