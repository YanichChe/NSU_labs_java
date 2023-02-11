package main.java.ru.nsu.fit.yana.task2.commands;

import main.java.ru.nsu.fit.yana.task2.Command;
import main.java.ru.nsu.fit.yana.task2.Context;
import main.java.ru.nsu.fit.yana.task2.exceptions.NegativeNumberException;

import java.util.Deque;
import java.util.EmptyStackException;

public class Sqrt extends Command
{
    @Override
    public void load(String[] args, Context ctx) throws EmptyStackException, NegativeNumberException
    {
        Deque<Double> stack = ctx.getStack();

        if (stack.size() == 0) throw new EmptyStackException();
        Double var = stack.pop();

        if (var < 0) throw new NegativeNumberException();

        stack.push(Math.sqrt(var));
    }
}
