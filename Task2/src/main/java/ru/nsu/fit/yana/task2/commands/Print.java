package ru.nsu.fit.yana.task2.commands;

import ru.nsu.fit.yana.task2.Command;
import ru.nsu.fit.yana.task2.Context;
import ru.nsu.fit.yana.task2.exceptions.ArgumentsSizeException;
import ru.nsu.fit.yana.task2.exceptions.StackSizeException;

import java.util.Deque;

public class Print extends Command
{
    @Override
    public void load(String[] args, Context ctx) throws StackSizeException
    {
        Deque<Double> stack = ctx.getStack();

        if (args.length != 1) throw new ArgumentsSizeException();
        if (stack.size() == 0) throw new StackSizeException();

        System.out.println("Result is " + stack.peek());
    }
}
