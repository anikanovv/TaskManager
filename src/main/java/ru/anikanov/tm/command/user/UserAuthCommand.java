package ru.anikanov.tm.command.user;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class UserAuthCommand extends AbstractCommand {
    public UserAuthCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "auth user";
    }

    @Override
    public String getDescription() {
        return "authorization user";
    }

    @Override
    public void execute() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (isSecure()) {
            System.out.println("you need to end session");
            return;
        }
        System.out.println("login");
        String login = scanner.nextLine();
        System.out.println("pass");
        String pass = scanner.nextLine();
        if (bootstrap.userService.auth(login, pass)) {
            bootstrap.setCurrentUser(bootstrap.userService.findOne(login).getLogin());
            System.out.println("AUTH OK!");
            System.out.println(bootstrap.userService.findOne(login).getRole());
        }
    }
}
