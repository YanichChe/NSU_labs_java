package ru.nsu.fit.yana.task2.exceptions;

public class NegativeNumberException extends ArithmeticException
{
    public NegativeNumberException()
    {
        super("number is negative");
    }
}
