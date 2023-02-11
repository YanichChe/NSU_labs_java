package main.java.ru.nsu.fit.yana.task2;


import java.io.InputStream;

public class Main
{
    public static void main(String[] args)
    {
        Parser parser = new Parser(args);
        parser.parse();

        InputStream in = parser.getInputStream();
        Context ctx = new Context();
    }
}
