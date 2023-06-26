package test.ru.nsu.fit.yana.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.fit.yana.task2.commands.Command;
import ru.nsu.fit.yana.task2.data.Context;
import ru.nsu.fit.yana.task2.commands.Define;
import ru.nsu.fit.yana.task2.commands.Push;
import ru.nsu.fit.yana.task2.exceptions.ArgumentsSizeException;
import ru.nsu.fit.yana.task2.exceptions.UndefinedVariableException;

import java.util.stream.Stream;

class PushTest
{
    private Push push = new Push();
    private Define define = new Define();
    private Context context = new Context();

    @ParameterizedTest
    @MethodSource("generateTestCorrectArgs")
    public void loadCorrectTest(String[] aArgv, String[] bArgv, double result) throws UndefinedVariableException
    {
        define.load(aArgv, context);
        push.load(bArgv, context);
        Assertions.assertEquals(context.getStack().peek(), result);
    }

    @ParameterizedTest
    @MethodSource("generateTestIncorrectArgs")
    public void loadIncorrectTest(String[] argv, Class<? extends Throwable> exceptionClass)
    {
        try
        {
            push.load(argv, context);
        }

        catch (ArgumentsSizeException | NumberFormatException | UndefinedVariableException e)
        {
            Assertions.assertEquals(e.getClass(), exceptionClass);
        }
    }
    private static Stream<Arguments> generateTestCorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.DEFINE, "a", "4"}, new String[]{Command.PUSH, "a"}, 4.0)
        );
    }

    private static Stream<Arguments> generateTestIncorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{Command.PUSH}, ArgumentsSizeException.class),
                Arguments.of(new String[]{Command.PUSH, "z"}, UndefinedVariableException.class)
        );
    }
}