package ru.anikanov.tm.command.user;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.Enum.Role;
import ru.anikanov.tm.command.AbstractCommand;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class UserUpdateCommand extends AbstractCommand {
    public UserUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "update user";
    }

    @Override
    public String getDescription() {
        return "updating user";
    }

    @Override
    public void execute() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (!isSecure()) return;
        String login = scanner.next();
        String pass = scanner.next();
        String newRole = scanner.next();
        Role role;
        if (newRole.equals("admin")) role = Role.ADMIN;
        else role = Role.USER;
        bootstrap.userService.merge(login, pass, role);
    }
}
