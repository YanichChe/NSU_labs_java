package ru.nsu.fit.yana.task2;


import org.apache.log4j.Logger;
import ru.nsu.fit.yana.task2.exceptions.UndefinedFileNameException;

import java.io.InputStream;
import java.util.Scanner;

public class Main
{
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    public static void main(String[] args) throws UndefinedFileNameException
    {
        LOGGER.info("Initialize Calc application");

        LOGGER.info("Parsing command line");
        CommandLineParser parser = new CommandLineParser(args);
        parser.parse();

        LOGGER.info("Create context");
        InputStream in = parser.getInputStream();
        Context ctx = new Context();

        LOGGER.info("load Calc");
        try(Scanner scanner = new Scanner(in))
        {
            while(scanner.hasNextLine())
            {
                try
                {
                String[] arr = scanner.nextLine().split(" ");

                LOGGER.info("try to create command " + arr[0]);
                Command command = CommandFactory.createCommand(arr[0]);
                LOGGER.info("the command was created successfully");

                command.load(arr, ctx);
                }
                catch (Exception e)
                {
                    LOGGER.error("", e);
                    System.err.println(e.getMessage());

                }
            }
        }

        LOGGER.info("END");
    }
}
