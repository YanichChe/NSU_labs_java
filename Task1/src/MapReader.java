import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class MapReader
{
    private final String fileName;
    private final Map<String, Integer> map;
    private int countWords;

    public MapReader(final String fileName)
    {
        this.fileName = fileName;
        this.map = new HashMap<>();
        countWords = 0;
    }

    public  Map<String, Integer> getMap()
    {
        return map;
    }

    public void readMap() throws IOException
    {
        try(Scanner in = new Scanner(new FileInputStream(fileName)))
        {
            while(in.hasNext())
            {
                String word = in.next();
                word = word.toLowerCase(Locale.ROOT);
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0; i < word.length(); i++)
                {
                    if (Character.isLetterOrDigit(word.charAt(i)) || word.charAt(i) == '-')
                        stringBuilder.append(word.charAt(i));
                }

                map.merge(stringBuilder.toString(), 1, Integer::sum);
                countWords++;
            }
        }
    }

    public int getCountWords()
    {
        return countWords;
    }
}

