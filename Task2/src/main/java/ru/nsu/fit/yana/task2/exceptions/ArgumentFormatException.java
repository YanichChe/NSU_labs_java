package ru.nsu.fit.yana.task2.exceptions;

public class ArgumentFormatException extends NumberFormatException
{
    public ArgumentFormatException()
    {
        super("wrong argument format");
    }
}
