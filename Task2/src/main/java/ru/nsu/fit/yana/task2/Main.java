package ru.nsu.fit.yana.task2;

import com.google.devtools.common.options.OptionsParser;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import ru.nsu.fit.yana.task2.commandLineParser.ConsoleArguments;
import ru.nsu.fit.yana.task2.commandLineParser.ConsoleArgumentsData;
import ru.nsu.fit.yana.task2.commands.Command;
import ru.nsu.fit.yana.task2.commands.CommandFactory;
import ru.nsu.fit.yana.task2.data.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Scanner;

@Log4j2
public class Main {
    public static void main(String[] args) throws IOException {
        log.info("Initialize Calc application");
        log.info("Parsing command line");

        OptionsParser parser = OptionsParser.newOptionsParser(ConsoleArgumentsData.class);
        parser.parseAndExitUponError(args);

        ConsoleArguments options = new ConsoleArguments(parser.getOptions(ConsoleArgumentsData.class));
        System.out.println(parser.describeOptions(Collections.<String, String>emptyMap(), OptionsParser.HelpVerbosity.LONG));

        InputStream in = createInputStream(options);
        if (in == null) return;

        log.info("Create context");
        Context ctx = new Context();

        log.info("load Calc");
        start(in, ctx);
        log.info("END");
        in.close();
    }

    private static void start(InputStream in, Context ctx){
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
    }

    private static InputStream createInputStream(ConsoleArguments options){
        InputStream in;

        if (options.getFile().isEmpty()) {
            in = System.in;
        } else {
            try {
                in = new FileInputStream(options.getFile());
            }
            catch (FileNotFoundException e) {
                log.error(e.getMessage());
                return null;
            }
        }

        return in;
    }
}
