package ru.anikanov.tm.command;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.ServiceLocator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Scanner;

public abstract class AbstractCommand {
    protected ServiceLocator bootstrap = new Bootstrap();
    protected final Scanner scanner = new Scanner(System.in);

    public abstract String getName();

    public abstract String getDescription();

    public abstract boolean isSecure();

    public abstract void execute() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException;
}
