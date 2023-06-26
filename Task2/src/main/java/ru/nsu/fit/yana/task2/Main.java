package ru.nsu.fit.yana.task2;

import lombok.extern.log4j.Log4j2;
import ru.nsu.fit.yana.task2.exceptions.UndefinedFileNameException;
import java.io.InputStream;
import java.util.Scanner;

@Log4j2
public class Main {
    public static void main(String[] args) throws UndefinedFileNameException {
        log.info("Initialize Calc application");
        log.info("Parsing command line");

        CommandLineParser parser = new CommandLineParser(args);
        parser.parse();

        log.info("Create context");
        InputStream in = parser.getInputStream();
        Context ctx = new Context();

        log.info("load Calc");
        try (Scanner scanner = new Scanner(in)) {
            while (scanner.hasNextLine()) {
                try {
                    String[] arr = scanner.nextLine().split(" ");

                    log.info("try to create command " + arr[0]);
                    Command command = CommandFactory.createCommand(arr[0]);
                    log.info("the command was created successfully");

                    command.load(arr, ctx);
                }
                catch (Exception e) {
                    log.error("", e);
                    System.err.println(e.getMessage());

                }
            }
        }
        log.info("END");
    }
}
