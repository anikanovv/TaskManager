package ru.anikanov.tm.service;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.ITerminalService;

import java.util.Scanner;

public class TerminalService implements ITerminalService {
    @Getter
    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @Nullable
    public String nextLine() {
        return scanner.nextLine();
    }
}
