package ru.nsu.fit.yana.task2.commandLineParser;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

public class ConsoleArgumentsData extends OptionsBase {
    public ConsoleArgumentsData()
    {
    }
    @Option(
            name = "help",
            abbrev = 'h',
            help = "Prints usage info.",
            defaultValue = "true"
    )
    public boolean help;

    @Option(
            name = "file",
            abbrev = 'f',
            help = "File with commands.",
            defaultValue = ""
    )
    public String file;
}
