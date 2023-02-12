package ru.nsu.fit.yana.task2.exceptions;

public class ArgumentsSizeException extends Exception
{
    public ArgumentsSizeException()
    {
        super("Not enough elements in the argv");
    }
}
