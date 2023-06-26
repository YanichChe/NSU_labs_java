package ru.nsu.fit.yana.task2.exceptions;

public class ArgumentsSizeException extends ArrayIndexOutOfBoundsException {
    public ArgumentsSizeException() {
        super("Not enough elements in the argv");
    }
}
