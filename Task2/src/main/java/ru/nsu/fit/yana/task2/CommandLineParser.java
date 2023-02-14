package ru.nsu.fit.yana.task2;

import org.apache.commons.cli.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CommandLineParser
{
    private InputStream in = System.in;
    private final String[] args;

    public CommandLineParser(String[] args)
    {
        this.args = args;
    }

    public void parse()
    {

        Options options = new Options();

        options.addOption(Option.builder("f")
                .longOpt("file_name")
                .desc("your file with commands")
                .hasArg(true)
                .build());

        options.addOption(Option.builder("h")
                .longOpt("help")
                .desc("print this message")
                .build());

        HelpFormatter helpFormatter = new HelpFormatter();
        org.apache.commons.cli.CommandLineParser commandLineParser = new DefaultParser();
        try
        {
            CommandLine commandLine = commandLineParser.parse(options, args);
            if (((CommandLine) commandLine).hasOption("help"))
            {
                helpFormatter.printHelp("cmd", options);
            }

            if (commandLine.hasOption("file_name"))
            {
                String file = commandLine.getOptionValue("file_name");
                System.out.println("Reading from " + file);
                this.in = new FileInputStream(file);
            }
        }
        catch (ParseException | FileNotFoundException e)
        {
            helpFormatter.printHelp("cmd", options);
            e.printStackTrace();
        }
    }

    public InputStream getInputStream()
    {
        return in;
    }
}
