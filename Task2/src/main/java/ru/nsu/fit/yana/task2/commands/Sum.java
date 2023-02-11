package main.java.ru.nsu.fit.yana.task2.commands;

import main.java.ru.nsu.fit.yana.task2.Command;
import main.java.ru.nsu.fit.yana.task2.Context;

import java.util.Deque;
import java.util.EmptyStackException;

public class Sum extends Command
{
    @Override
    public void load(String[] args, Context ctx) throws EmptyStackException
    {
        Deque<Double> stack = ctx.getStack();
        Double var1 = stack.pop();
        Double var2 = stack.pop();

        if (stack.size() < 2) throw new EmptyStackException();

        stack.push(var1 + var2);
    }
}
