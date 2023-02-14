package ru.nsu.fit.yana.task2;

import ru.nsu.fit.yana.task2.exceptions.ArgumentsSizeException;
import ru.nsu.fit.yana.task2.exceptions.StackSizeException;
import ru.nsu.fit.yana.task2.exceptions.UndefinedVariableException;

public abstract class Command
{
    public static final String DEFINE = "DEFINE ";
    public static final String DIVISION = "DIVISION";
    public static final String MULTIPLICATION = "MULTIPLICATION";
    public static final String POP = "POP";
    public static final String PRINT ="PRINT";
    public static final String PUSH = "PUSH";
    public static final String SQRT = "SQRT";
    public static final String SUBTRACTION = "SUBTRACTION";
    public static final String SUM  = "SUM";

    public abstract void load(String[] args, Context ctx) throws ArrayIndexOutOfBoundsException, StackSizeException, ArgumentsSizeException, UndefinedVariableException;
}
