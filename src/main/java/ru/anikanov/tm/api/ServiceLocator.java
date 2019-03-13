package ru.anikanov.tm.api;

import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;

public interface ServiceLocator {
    Scanner getScanner();

    String getCurrentUser();

    void setCurrentUser(String currentUser);

    IProjectService getProjectService();

    ITaskService getTaskService();

    Map<String, AbstractCommand> getCommandMap();

    IUserService getUserService();

    void initCommands();

    String passwordHash(String string);

    void init() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException;
}
