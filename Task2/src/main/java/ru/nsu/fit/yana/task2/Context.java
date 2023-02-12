package ru.nsu.fit.yana.task2;

import java.util.*;

public class Context
{
    private Map<String, Double>  variables = new HashMap<>();
    private Deque<Double> stack = new ArrayDeque<>();

    public Map<String, Double> getVariables()
    {
        return variables;
    }

    public  Deque<Double> getStack()
    {
        return stack;
    }
}
