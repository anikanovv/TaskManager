package ru.anikanov.tm.command;

import ru.anikanov.tm.Bootstrap;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Scanner;

public abstract class AbstractCommand {
    protected Bootstrap bootstrap;
    protected final Scanner scanner = new Scanner(System.in);

    public AbstractCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract boolean isSecure();

    public abstract void execute() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException;
}
