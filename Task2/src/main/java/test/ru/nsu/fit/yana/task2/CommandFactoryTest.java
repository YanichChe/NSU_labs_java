package test.ru.nsu.fit.yana.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.fit.yana.task2.Command;
import ru.nsu.fit.yana.task2.CommandFactory;
import ru.nsu.fit.yana.task2.commands.*;
import ru.nsu.fit.yana.task2.exceptions.ClassCreationException;

import java.util.stream.Stream;

class CommandFactoryTest
{
    @ParameterizedTest
    @MethodSource("generateTestCorrectArgs")
    public void createCorrectCommandTest(String commandName, Class<? extends Command> commandClass)
            throws ClassCreationException
    {
        Assertions.assertEquals(CommandFactory.createCommand(commandName).getClass(), commandClass);
    }

    @ParameterizedTest
    @MethodSource("generateTestIncorrectArgs")
    public void createIncorrectCommandTest(String commandName, Class<? extends Throwable> exceptionClass)
            throws ClassCreationException
    {
        try
        {
            CommandFactory.createCommand(commandName);
        }

        catch (ClassCreationException e)
        {
            Assertions.assertEquals(e.getClass(), exceptionClass);
        }
    }

    private static Stream<Arguments> generateTestCorrectArgs()
    {
        return Stream.of(
                Arguments.of("DEFINE", Define.class),
                Arguments.of("/", Division.class),
                Arguments.of("*", Multiplication.class),
                Arguments.of("POP", Pop.class),
                Arguments.of("PRINT", Print.class),
                Arguments.of("PUSH", Push.class),
                Arguments.of("SQRT", Sqrt.class),
                Arguments.of("-", Subtraction.class),
                Arguments.of("+", Sum.class)
                );
    }
    private static Stream<Arguments> generateTestIncorrectArgs()
    {
        return Stream.of(
                Arguments.of("PUPOK", ClassCreationException.class),
                Arguments.of("Z", ClassCreationException.class)
        );
    }



}