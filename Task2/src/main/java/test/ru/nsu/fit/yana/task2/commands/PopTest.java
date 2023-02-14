package test.ru.nsu.fit.yana.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.fit.yana.task2.Command;
import ru.nsu.fit.yana.task2.Context;
import ru.nsu.fit.yana.task2.commands.Pop;
import ru.nsu.fit.yana.task2.exceptions.StackSizeException;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

class PopTest
{
    private Pop pop = new Pop();
    private Context context = new Context();

    @ParameterizedTest
    @MethodSource("generateTestCorrectArgs")
    public void loadCorrectTest(String[] argv, ArrayList<Double> arrayList, double result) throws StackSizeException
    {
        init(context, arrayList);
        pop.load(argv, context);
        Assertions.assertEquals(context.getStack().peek(), result);
    }

    @ParameterizedTest
    @MethodSource("generateTestIncorrectArgs")
    public void loadIncorrectTest(String[] argv, ArrayList<Double> arrayList, Class<? extends Throwable> exceptionClass)
    {
        try
        {
            init(context, arrayList);
            pop.load(argv, context);
        }

        catch (StackSizeException e)
        {
            Assertions.assertEquals(e.getClass(), exceptionClass);
        }
    }
    private static Stream<Arguments> generateTestCorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.POP}, new ArrayList<>(List.of(4.0, 8.0)), 4.0)
        );
    }

    private static Stream<Arguments> generateTestIncorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.POP}, new ArrayList<>(List.of()), StackSizeException.class)
        );
    }

    public static void init(Context context, ArrayList<Double> arrayList)
    {
        Deque<Double> stack = context.getStack();

        for (Double i : arrayList)
        {
            stack.push(i);
        }
    }
}