package ru.anikanov.tm;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class App {
    public static void main(String[] args) throws IOException, ParseException, NoSuchAlgorithmException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}
