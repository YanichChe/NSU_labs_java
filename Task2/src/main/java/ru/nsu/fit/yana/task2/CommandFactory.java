package ru.nsu.fit.yana.task2;

import ru.nsu.fit.yana.task2.exceptions.ClassCreationException;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class CommandFactory {
    public static Command createCommand(String name) throws ClassCreationException {
        Properties properties = new Properties();
        Command command;

        try {
            properties.load(CommandFactory.class.getClassLoader().getResourceAsStream("class_names.properties"));

            Class<?> c = Class.forName(properties.getProperty(name));
            Constructor<?> constructor = c.getConstructor();
            command = (Command) constructor.newInstance();
        }

        catch (NullPointerException | IllegalAccessException | IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            throw new ClassCreationException();
        }

        return command;
    }
}
