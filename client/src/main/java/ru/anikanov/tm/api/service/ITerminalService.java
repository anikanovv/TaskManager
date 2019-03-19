package ru.anikanov.tm.api.service;


import org.jetbrains.annotations.Nullable;

public interface ITerminalService {
    void terminalCicle() throws Exception;

    @Nullable
    String nextLine();
}
