package ru.nsu.fit.yana.task2.exceptions;

public class ClassCreationException extends Exception {
    public ClassCreationException() {
        super("not such command");
    }
}
