package ru.anikanov.tm.command.user;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class UserUpdatePasswordCommand extends AbstractCommand {
    public UserUpdatePasswordCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public String getName() {
        return "updatepass user";
    }

    @Override
    public String getDescription() {
        return "to update user password";
    }

    @Override
    public void execute() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String login = scanner.nextLine();
        String oldPass = bootstrap.passwordHash(scanner.nextLine());
        String newPass = bootstrap.passwordHash(scanner.nextLine());
        if (bootstrap.userService.updatePassword(login, oldPass, newPass)) System.out.println("updated");
    }
}
