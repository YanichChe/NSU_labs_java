package ru.nsu.fit.yana.task2.data;

import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;

public class Context {
    private final Map<String, Double> variables = new HashMap<>();
    private final Deque<Double> stack = new ArrayDeque<>();

    public Map<String, Double> getVariables() {
        return variables;
    }

    public Deque<Double> getStack() {
        return stack;
    }
}
