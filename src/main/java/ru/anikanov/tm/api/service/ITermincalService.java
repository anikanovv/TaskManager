package ru.anikanov.tm.api.service;

import java.util.Scanner;

public interface ITermincalService {
    //    Scanner getScanner();
    void terminalCicle() throws Exception;

    String nextLine();
}
