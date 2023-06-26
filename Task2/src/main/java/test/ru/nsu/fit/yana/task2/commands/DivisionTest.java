package test.ru.nsu.fit.yana.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.fit.yana.task2.commands.Command;
import ru.nsu.fit.yana.task2.data.Context;
import ru.nsu.fit.yana.task2.commands.Division;
import ru.nsu.fit.yana.task2.exceptions.DivisionByZeroException;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Stream;

class DivisionTest
{
    private Division division = new Division();
    private Context context = new Context();

    @ParameterizedTest
    @MethodSource("generateTestCorrectArgs")
    public void loadCorrectTest(String[] argv, ArrayList<Double> arrayList)
    {
        PopTest.init(context, arrayList);
        division.load(argv, context);
        Assertions.assertEquals(context.getStack().peek(), arrayList.get(1) / arrayList.get(0));
    }

    @ParameterizedTest
    @MethodSource("generateTestIncorrectArgs")
    public void loadIncorrectTest(String[] argv, ArrayList<Double> arrayList, Class<? extends Throwable> exceptionClass)
    {
        try
        {
            PopTest.init(context, arrayList);
            division.load(argv, context);
        }

        catch (EmptyStackException | DivisionByZeroException e)
        {
            Assertions.assertEquals(e.getClass(), exceptionClass);
        }
    }
    private static Stream<Arguments> generateTestCorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.DIVISION}, new ArrayList<>(List.of(4.0, 8.0))),
                Arguments.of(new String[]{Command.DIVISION}, new ArrayList<>(List.of(89.0, 55555.0))),
                Arguments.of(new String[]{Command.DIVISION}, new ArrayList<>(List.of(66.0, -369878.0)))
        );
    }

    private static Stream<Arguments> generateTestIncorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.DIVISION}, new ArrayList<>(List.of(8.0)), EmptyStackException.class),
                Arguments.of(new String[]{Command.DIVISION}, new ArrayList<>(List.of(0.0, 8.0)), DivisionByZeroException.class)
        );
    }

}