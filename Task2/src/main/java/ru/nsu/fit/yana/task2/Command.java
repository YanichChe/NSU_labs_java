package main.java.ru.nsu.fit.yana.task2;

import main.java.ru.nsu.fit.yana.task2.exceptions.ArgumentsSizeException;
import main.java.ru.nsu.fit.yana.task2.exceptions.StackSizeException;

public abstract class Command
{
    public abstract void load(String[] args, Context ctx) throws StackSizeException, ArgumentsSizeException;
}
