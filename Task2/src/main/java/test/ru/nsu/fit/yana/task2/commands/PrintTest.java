package test.ru.nsu.fit.yana.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.fit.yana.task2.commands.Command;
import ru.nsu.fit.yana.task2.data.Context;
import ru.nsu.fit.yana.task2.commands.Define;
import ru.nsu.fit.yana.task2.commands.Print;
import ru.nsu.fit.yana.task2.exceptions.ArgumentsSizeException;
import ru.nsu.fit.yana.task2.exceptions.StackSizeException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class PrintTest
{
    private Print print = new Print();
    private Define define = new Define();
    private Context context = new Context();

    @ParameterizedTest
    @MethodSource("generateTestCorrectArgs")
    public void loadCorrectTest(String[] argv, ArrayList<Double> arrayList, double result) throws StackSizeException
    {
        PopTest.init(context, arrayList);
        Assertions.assertDoesNotThrow(() -> print.load(argv, context));
    }

    @ParameterizedTest
    @MethodSource("generateTestIncorrectArgs")
    public void loadIncorrectTest(String[] argv, ArrayList<Double> arrayList, Class<? extends Throwable> exceptionClass)
    {
        try
        {
            PopTest.init(context, arrayList);
            print.load(argv, context);
        }

        catch (StackSizeException | ArgumentsSizeException e)
        {
            Assertions.assertEquals(e.getClass(), exceptionClass);
        }
    }

    private static Stream<Arguments> generateTestCorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.PRINT}, new ArrayList<>(List.of(4.0, 8.0)), 4.0)
        );
    }

    private static Stream<Arguments> generateTestIncorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.PRINT, "hello"},  new ArrayList<>(List.of()), ArgumentsSizeException.class),
                Arguments.of(new String[]{Command.PRINT},  new ArrayList<>(List.of()), StackSizeException.class)
        );
    }
}