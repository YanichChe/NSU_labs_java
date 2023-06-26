package ru.nsu.fit.yana.task2.commands;

import ru.nsu.fit.yana.task2.data.Context;
import ru.nsu.fit.yana.task2.exceptions.ArgumentFormatException;
import ru.nsu.fit.yana.task2.exceptions.ArgumentsSizeException;

import java.util.Map;

public class Define extends Command {
    @Override
    public void load(String[] args, Context ctx) throws ArgumentsSizeException, NumberFormatException {
        Map<String, Double> variables = ctx.getVariables();
        if (args.length != 3)
            throw new ArgumentsSizeException();

        try {
            variables.put(args[1], Double.valueOf(args[2]));
        }
        catch (NumberFormatException e) {
            throw new ArgumentFormatException();
        }
    }
}
