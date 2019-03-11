package ru.anikanov.tm.command.system;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class HelpCommand extends AbstractCommand {
    public HelpCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "information about all commands";
    }

    @Override
    public boolean isSecure() {
        return true;
    }
    @Override
    public void execute() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        bootstrap.commandMap.forEach((k, v) -> System.out.println(v.getName() + " - " + v.getDescription()));
    }
}
