package test.ru.nsu.fit.yana.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.fit.yana.task2.Command;
import ru.nsu.fit.yana.task2.Context;
import ru.nsu.fit.yana.task2.commands.Multiplication;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Stream;

class MultiplicationTest
{

    private Multiplication multiplication = new Multiplication();
    private Context context = new Context();

    @ParameterizedTest
    @MethodSource("generateTestCorrectArgs")
    public void loadCorrectTest(String[] argv, ArrayList<Double> arrayList)
    {
        PopTest.init(context, arrayList);
        multiplication.load(argv, context);
        Assertions.assertEquals(context.getStack().peek(), arrayList.get(1) * arrayList.get(0));
    }

    @ParameterizedTest
    @MethodSource("generateTestIncorrectArgs")
    public void loadIncorrectTest(String[] argv, ArrayList<Double> arrayList, Class<? extends Throwable> exceptionClass)
    {
        try
        {
            PopTest.init(context, arrayList);
            multiplication.load(argv, context);
        }

        catch (EmptyStackException e)
        {
            Assertions.assertEquals(e.getClass(), exceptionClass);
        }
    }
    private static Stream<Arguments> generateTestCorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.MULTIPLICATION}, new ArrayList<>(List.of(4.0, 8.0))),
                Arguments.of(new String[]{Command.MULTIPLICATION}, new ArrayList<>(List.of(89.0, 55555.0))),
                Arguments.of(new String[]{Command.MULTIPLICATION}, new ArrayList<>(List.of(66.78, -369878.87)))
        );
    }

    private static Stream<Arguments> generateTestIncorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.MULTIPLICATION}, new ArrayList<>(List.of(8.0)), EmptyStackException.class)
        );
    }
}