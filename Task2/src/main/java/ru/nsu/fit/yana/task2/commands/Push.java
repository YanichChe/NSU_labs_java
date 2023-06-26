package ru.nsu.fit.yana.task2.commands;

import ru.nsu.fit.yana.task2.Command;
import ru.nsu.fit.yana.task2.Context;
import ru.nsu.fit.yana.task2.exceptions.ArgumentFormatException;
import ru.nsu.fit.yana.task2.exceptions.ArgumentsSizeException;
import ru.nsu.fit.yana.task2.exceptions.UndefinedVariableException;

import java.util.Deque;
import java.util.Map;

public class Push extends Command {
    @Override
    public void load(String[] args, Context ctx) throws ArgumentsSizeException, ArgumentFormatException, UndefinedVariableException {
        Deque<Double> stack = ctx.getStack();
        Map<String, Double> variables = ctx.getVariables();

        if (args.length < 2)
            throw new ArgumentsSizeException();

        try {
            var actualValue = 0.0;
            if (variables.containsKey(args[1])) {
                actualValue = variables.get(args[1]);
            } else {
                actualValue = Double.valueOf(args[1]);
            }

            stack.push(actualValue);
        }

        catch (NullPointerException e) {
            throw new UndefinedVariableException();
        }
    }
}
