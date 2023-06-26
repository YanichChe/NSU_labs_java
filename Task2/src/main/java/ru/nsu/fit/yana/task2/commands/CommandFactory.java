package ru.nsu.fit.yana.task2.commands;

import lombok.NonNull;
import ru.nsu.fit.yana.task2.commands.*;
import ru.nsu.fit.yana.task2.exceptions.ClassCreationException;
import java.util.Map;

public class CommandFactory {

    @FunctionalInterface
    private interface CommandConstructor {
        @NonNull Command getCommand();
    }

    private static final @NonNull Map<CommandType, CommandConstructor> COMMAND_FACTORIES = Map.of(
            CommandType.DEFINE, Define::new,
            CommandType.DIVISION, Division::new,
            CommandType.MULTIPLICATION, Multiplication::new,
            CommandType.POP, Pop::new,
            CommandType.PRINT, Print::new,
            CommandType.PUSH, Push::new,
            CommandType.SQRT, Sqrt::new,
            CommandType.SUBTRACTION, Subtraction::new,
            CommandType.SUM, Sum::new
    );

    public static Command createCommand(@NonNull String name) throws ClassCreationException {
        return COMMAND_FACTORIES.get(CommandType.valueOf(name)).getCommand();
    }
}
