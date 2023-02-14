package ru.nsu.fit.yana.task2;


import java.io.InputStream;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        CommandLineParser parser = new CommandLineParser(args);
        parser.parse();

        InputStream in = parser.getInputStream();
        Context ctx = new Context();

        try(Scanner scanner = new Scanner(in))
        {
            while(scanner.hasNextLine())
            {
                try
                {
                String[] arr = scanner.nextLine().split(" ");
                Command command = CommandFactory.createCommand(arr[0]);
                command.load(arr, ctx);
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());;
                }
            }
        }
    }
}
