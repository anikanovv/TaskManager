package ru.anikanov.tm;

import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}
