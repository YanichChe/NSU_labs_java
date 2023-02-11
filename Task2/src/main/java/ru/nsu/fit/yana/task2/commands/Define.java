package main.java.ru.nsu.fit.yana.task2.commands;

import main.java.ru.nsu.fit.yana.task2.Command;
import main.java.ru.nsu.fit.yana.task2.Context;

import java.util.Map;

public class Define extends Command
{
    @Override
    public void load(String[] args, Context ctx)
    {
        Map<String, Double> variables = ctx.getVariables();
        variables.put(args[1], Double.valueOf(args[2]));
    }
}
