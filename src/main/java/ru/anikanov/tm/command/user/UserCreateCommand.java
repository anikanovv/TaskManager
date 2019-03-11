package ru.anikanov.tm.command.user;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.Enum.Role;
import ru.anikanov.tm.command.AbstractCommand;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class UserCreateCommand extends AbstractCommand {

    public UserCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "register user";
    }

    @Override
    public String getDescription() {
        return "create new user";
    }

    @Override
    public void execute() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (!isSecure()) return;
        System.out.println("Name,pass,role");
        String userName = scanner.nextLine();
        String userPass = scanner.nextLine();
        String userRole = scanner.nextLine();
        Role role;
        if (userRole.equals("admin")) role = Role.ADMIN;
        else role = Role.USER;
        bootstrap.userService.persist(userName, userPass, role);
    }
}
