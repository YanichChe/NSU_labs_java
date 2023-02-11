package main.java.ru.nsu.fit.yana.task2.commands;

import main.java.ru.nsu.fit.yana.task2.Command;
import main.java.ru.nsu.fit.yana.task2.Context;
import main.java.ru.nsu.fit.yana.task2.exceptions.ArgumentsSizeException;

import java.util.Deque;

public class Push extends Command
{
    @Override
    public void load(String[] args, Context ctx) throws ArgumentsSizeException
    {
        Deque<Double> stack = ctx.getStack();
        if (args.length == 0) throw new ArgumentsSizeException();
        stack.push(Double.valueOf(args[1]));
    }
}
