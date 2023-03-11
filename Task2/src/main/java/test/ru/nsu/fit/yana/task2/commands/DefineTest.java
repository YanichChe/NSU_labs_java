package test.ru.nsu.fit.yana.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.fit.yana.task2.Command;
import ru.nsu.fit.yana.task2.Context;
import ru.nsu.fit.yana.task2.commands.Define;
import ru.nsu.fit.yana.task2.exceptions.ArgumentFormatException;
import ru.nsu.fit.yana.task2.exceptions.ArgumentsSizeException;

import java.util.Map;
import java.util.stream.Stream;

class DefineTest
{
    private Define define = new Define();
    private Context context = new Context();

    @ParameterizedTest
    @MethodSource("generateTestCorrectArgs")
    public void loadCorrectTest(String[] argv, Object obj)
    {
        define.load(argv, context);
        Map<String, Double> map = Map.of(argv[1], Double.valueOf(obj.toString()));

        Assertions.assertTrue(map.equals(context.getVariables()));
    }

    @ParameterizedTest
    @MethodSource("generateTestIncorrectArgs")
    public void loadIncorrectTest(String[] argv, Class<? extends Throwable> exceptionClass)
    {
        try
        {
            define.load(argv, context);
        }

        catch (NumberFormatException | ArgumentsSizeException e)
        {
            Assertions.assertEquals(e.getClass(), exceptionClass);
        }
    }

    private static Stream<Arguments> generateTestCorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.DEFINE, "a", "8"}, 8),
                Arguments.of(new String[]{Command.DEFINE, "a", "8.0"}, 8.0),
                Arguments.of(new String[]{Command.DEFINE, "a", "0"}, 0),
                Arguments.of(new String[]{Command.DEFINE, "a", "-55"}, -55),
                Arguments.of(new String[]{Command.DEFINE, "a", "-555.3"}, -555.3)
        );
    }

    private static Stream<Arguments> generateTestIncorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.DEFINE, "a", "a"}, ArgumentFormatException.class),
                Arguments.of(new String[]{Command.DEFINE, "a"}, ArgumentsSizeException.class)
        );
    }
}