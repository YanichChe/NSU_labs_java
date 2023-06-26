package ru.nsu.fit.yana.task2.commands;

import ru.nsu.fit.yana.task2.Command;
import ru.nsu.fit.yana.task2.Context;

import java.util.Deque;
import java.util.EmptyStackException;

public class Multiplication extends Command {
    @Override
    public void load(String[] args, Context ctx) throws EmptyStackException {
        Deque<Double> stack = ctx.getStack();

        if (stack.size() < 2)
            throw new EmptyStackException();
        Double var1 = stack.pop();
        Double var2 = stack.pop();

        stack.push(var1 * var2);
    }
}
