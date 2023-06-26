package ru.nsu.fit.yana.task2.commandLineParser;

public class ConsoleArguments {
    private final ConsoleArgumentsData inner;

    public ConsoleArguments(ConsoleArgumentsData innerValues) {
        this.inner = innerValues;
    }

    public String getFile() {
        return this.inner.file;
    }
}
