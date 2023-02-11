package main.java.ru.nsu.fit.yana.task2.commands;

import main.java.ru.nsu.fit.yana.task2.Command;
import main.java.ru.nsu.fit.yana.task2.Context;
import main.java.ru.nsu.fit.yana.task2.exceptions.StackSizeException;

import java.util.Deque;

public class Print extends Command
{
    @Override
    public void load(String[] args, Context ctx) throws StackSizeException
    {
        Deque<Double> stack = ctx.getStack();

        if (stack.size() == 0) throw new StackSizeException();
        System.out.println("Last result is " + stack.peek());
    }
}
