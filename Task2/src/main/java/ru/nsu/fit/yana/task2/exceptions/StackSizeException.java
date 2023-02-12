package ru.nsu.fit.yana.task2.exceptions;

public class StackSizeException extends Exception
{
    public StackSizeException()
    {
        super("Not enough elements in the stack");
    }
}
