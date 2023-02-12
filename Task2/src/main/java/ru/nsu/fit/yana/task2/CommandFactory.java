package ru.nsu.fit.yana.task2;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommandFactory
{
    //TODO изменить на относительный путь
    public static final String fileName = "C:\\Users\\Yana228\\IdeaProjects\\NSU_labs_java\\Task2\\src\\main\\java\\ru\\nsu\\fit\\yana\\task2\\class_names.json";

    public static Command createCommand(String name)
    {
        JSONParser jsonParser = new JSONParser();
        Command command = null;

        try (FileReader reader = new FileReader(fileName))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject jo = (JSONObject) obj;
            String className = (String) jo.get(name);

            Class c = Class.forName(className);
            Constructor constructor = c.getConstructor();
            command = (Command) constructor.newInstance();
        }

        catch (IllegalAccessException | ParseException | IOException |
                ClassNotFoundException | InvocationTargetException |
                NoSuchMethodException | InstantiationException e)
        {
            e.printStackTrace();
        }

        return command;
    }
}
