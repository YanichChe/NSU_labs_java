import java.util.ArrayList;
import java.util.Map;

public class MapSorter
{
    private ArrayList<Map.Entry<String, Integer>> result;
    private final Map<String, Integer> map;

    public MapSorter(Map<String, Integer> map)
    {
        this.map = map;
    }

    public void sort()
    {
        result = new ArrayList<>();
        map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .reversed())
                .forEach(e->result.add(e));
    }
    public ArrayList<Map.Entry<String, Integer>> getResult()
    {
        return result;
    }
}
