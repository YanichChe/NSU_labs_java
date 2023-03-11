import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            final String fileName =  args[0];

            MapReader mapReader = new MapReader(fileName);
            mapReader.readMap();

            Map<String, Integer> map = mapReader.getMap();

            if (mapReader.getCountWords() == 0) System.out.println("Empty file");

            MapSorter mapSorter = new MapSorter(map);
            mapSorter.sort();

            CSVConverter csvConverter = new CSVConverter(mapSorter.getResult(),
                                                                mapReader.getCountWords());
            csvConverter.write();
        }

        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Not input files");
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
