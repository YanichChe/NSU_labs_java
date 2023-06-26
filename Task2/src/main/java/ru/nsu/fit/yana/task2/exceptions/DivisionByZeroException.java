package ru.nsu.fit.yana.task2.exceptions;

public class DivisionByZeroException extends ArithmeticException {
    public DivisionByZeroException() {
        super("division by zero");
    }
}
