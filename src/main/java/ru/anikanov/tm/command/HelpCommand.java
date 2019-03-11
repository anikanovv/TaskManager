package ru.anikanov.tm.command;

import ru.anikanov.tm.Bootstrap;

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
    public void execute() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        bootstrap.commandMap.forEach((k, v) -> System.out.println(v.getName() + " - " + v.getDescription()));
    }
}
