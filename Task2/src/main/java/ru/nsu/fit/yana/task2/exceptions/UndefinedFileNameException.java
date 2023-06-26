package ru.nsu.fit.yana.task2.exceptions;

import java.io.FileNotFoundException;

public class UndefinedFileNameException extends FileNotFoundException {
    public UndefinedFileNameException() {
        super("undefined file name");
    }
}
