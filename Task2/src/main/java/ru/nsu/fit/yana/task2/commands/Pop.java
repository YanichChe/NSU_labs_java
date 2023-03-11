package ru.nsu.fit.yana.task2.commands;

import ru.nsu.fit.yana.task2.Command;
import ru.nsu.fit.yana.task2.Context;
import ru.nsu.fit.yana.task2.exceptions.StackSizeException;

import java.util.Deque;

public class Pop extends Command
{
    @Override
    public void load(String[] args, Context ctx) throws StackSizeException
    {
        Deque<Double> stack = ctx.getStack();
        if (stack.size() == 0) throw new StackSizeException();
        stack.pop();
    }
}
