import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CSVConverter
{
    final public static int MAX_PERCENT = 100;
    final public static String OUTPUT_FILE_NAME = "output.csv";

    private final ArrayList<Map.Entry<String, Integer>> sortedMap;
    private final int countWords;

    public CSVConverter(ArrayList<Map.Entry<String, Integer>> sortedMap, final int countWords)
    {
        this.sortedMap = sortedMap;
        this.countWords = countWords;
    }

    public void write() throws IOException
    {
        File file = new File(OUTPUT_FILE_NAME);

        try(FileWriter fileWriter = new FileWriter(file))
        {
            for (Map.Entry<String, Integer> e: sortedMap)
            {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(e.getKey());
                stringBuilder.append(",");
                stringBuilder.append(e.getValue());
                stringBuilder.append(",");
                stringBuilder.append(getPercent(e));
                stringBuilder.append("\n");

                fileWriter.write(stringBuilder.toString());
            }
        }

    }

    private float getPercent(Map.Entry<String, Integer> e)
    {
        return (float)e.getValue() * MAX_PERCENT / countWords;
    }
}
