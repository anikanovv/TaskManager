package ru.anikanov.tm;

import ru.anikanov.tm.bootstrap.Bootstrap;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class App {
    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}
