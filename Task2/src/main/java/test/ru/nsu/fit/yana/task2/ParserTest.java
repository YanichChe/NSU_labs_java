package test.ru.nsu.fit.yana.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.fit.yana.task2.CommandLineParser;
import ru.nsu.fit.yana.task2.exceptions.UndefinedFileNameException;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

class ParserTest
{
    private CommandLineParser commandLineParser;

    @ParameterizedTest
    @MethodSource("generateTestIncorrectArgs")
    public void loadIncorrectTest(String[] argv, Class<? extends Throwable> exceptionClass)
    {
        try
        {
            commandLineParser = new CommandLineParser(argv);
            commandLineParser.parse();
        }
        catch (UndefinedFileNameException e)
        {
            Assertions.assertEquals(e.getClass(), exceptionClass);
        }
    }

    @ParameterizedTest
    @MethodSource("generateTestCorrectArgs")
    public void loadCorrectTest(String[] argv)
    {
        commandLineParser = new CommandLineParser(argv);
        Assertions.assertDoesNotThrow(() -> commandLineParser.parse());
    }

    private static Stream<Arguments> generateTestIncorrectArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{"--file_name", "C:\\Users\\Yana228\\IdeaProjects\\NSU_labs_java\\Task2\\src\\main\\java\\test\\ru\\nsu\\fit\\yana\\task2\\example.txt"}, FileNotFoundException.class)
        );
    }

    private static Stream <Arguments> generateTestCorrectArgs()
    {
        return Stream.of(
                Arguments.of((Object) new String[]{""}),
                Arguments.of((Object) new String[]{"-f", "C:\\Users\\Yana228\\IdeaProjects\\NSU_labs_java\\Task2\\src\\main\\java\\test\\ru\\nsu\\fit\\yana\\task2\\example.txt"}),
                Arguments.of((Object) new String[]{"-h"}),
                Arguments.of((Object) new String[]{"--file_name", "C:\\Users\\Yana228\\IdeaProjects\\NSU_labs_java\\Task2\\src\\main\\java\\test\\ru\\nsu\\fit\\yana\\task2\\example.txt"})
        );
    }
}