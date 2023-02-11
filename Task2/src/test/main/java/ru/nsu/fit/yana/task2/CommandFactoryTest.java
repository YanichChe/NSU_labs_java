package main.java.ru.nsu.fit.yana.task2;

import main.java.ru.nsu.fit.yana.task2.commands.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest
{
    @Test
    void createCommandDefine()
    {
        Command command = CommandFactory.createCommand("DEFINE");
        Command command1 = new Define();

        assertEquals(command.getClass(), command1.getClass());
    }

    @Test
    void createCommandDivision()
    {
        Command command = CommandFactory.createCommand("/");
        Command command1 = new Division();

        assertEquals(command.getClass(), command1.getClass());
    }

    @Test
    void createCommandMultiplication()
    {
        Command command = CommandFactory.createCommand("*");
        Command command1 = new Multiplication();

        assertEquals(command.getClass(), command1.getClass());
    }

    @Test
    void createCommandPop()
    {
        Command command = CommandFactory.createCommand("POP");
        Command command1 = new Pop();

        assertEquals(command.getClass(), command1.getClass());
    }

    @Test
    void createCommandPrint()
    {
        Command command = CommandFactory.createCommand("PRINT");
        Command command1 = new Print();

        assertEquals(command.getClass(), command1.getClass());
    }

    @Test
    void createCommandPush()
    {
        Command command = CommandFactory.createCommand("PUSH");
        Command command1 = new Push();

        assertEquals(command.getClass(), command1.getClass());
    }

    @Test
    void createCommandSqrt()
    {
        Command command = CommandFactory.createCommand("SQRT");
        Command command1 = new Sqrt();

        assertEquals(command.getClass(), command1.getClass());
    }

    @Test
    void createCommandSubtraction()
    {
        Command command = CommandFactory.createCommand("-");
        Command command1 = new Subtraction();

        assertEquals(command.getClass(), command1.getClass());
    }

    @Test
    void createCommandSum()
    {
        Command command = CommandFactory.createCommand("+");
        Command command1 = new Sum();

        assertEquals(command.getClass(), command1.getClass());
    }

    @Test
    void createCommandIllegal()
    {
        Throwable exception = assertThrows(NullPointerException.class, () -> CommandFactory.createCommand("Meow"));
        assertEquals(null, exception.getMessage());
    }

}