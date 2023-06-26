package test.ru.nsu.fit.yana.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.fit.yana.task2.commands.Command;
import ru.nsu.fit.yana.task2.data.Context;
import ru.nsu.fit.yana.task2.commands.Sqrt;
import ru.nsu.fit.yana.task2.exceptions.NegativeNumberException;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Stream;

class SqrtTest
{
    private Sqrt sqrt = new Sqrt();
    private Context context = new Context();

    @ParameterizedTest
    @MethodSource("generateTestCorrectArgs")
    public void loadCorrectTest(String[] argv, ArrayList<Double> arrayList)
    {
        PopTest.init(context, arrayList);
        sqrt.load(argv, context);
        Assertions.assertEquals(context.getStack().peek(), Math.sqrt(arrayList.get(0)));
    }

    @ParameterizedTest
    @MethodSource("generateTestIncorrectArgs")
    public void loadIncorrectTest(String[] argv, ArrayList<Double> arrayList, Class<? extends Throwable> exceptionClass)
    {
        try
        {
            PopTest.init(context, arrayList);
            sqrt.load(argv, context);
        }

        catch (EmptyStackException | NegativeNumberException e)
        {
            Assertions.assertEquals(e.getClass(), exceptionClass);
        }
    }

    private static Stream<Arguments> generateTestCorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.SQRT}, new ArrayList<>(List.of(4.0))),
                Arguments.of(new String[]{Command.SQRT}, new ArrayList<>(List.of(555555.0))),
                Arguments.of(new String[]{Command.SQRT}, new ArrayList<>(List.of(369878.87)))
        );
    }

    private static Stream<Arguments> generateTestIncorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.SQRT}, new ArrayList<>(List.of()), EmptyStackException.class),
                Arguments.of(new String[]{Command.SQRT}, new ArrayList<>(List.of(-555.0)), NegativeNumberException.class)
        );
    }
}