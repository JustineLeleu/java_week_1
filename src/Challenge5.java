import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Map;
import java.util.HashMap;

public class Challenge5
{
    public static Map<String, Integer> countWords(FileReader fr) throws IOException
    {
        Map<String, Integer> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(fr);
        String line;

        while ((line = reader.readLine()) != null)
        {
            String[] words = line.split(" ");
            for (String value : words)
            {
                String word = value.toLowerCase();
                if (map.containsKey(word))
                {
                    int count = map.get(word) + 1;
                    map.put(word, count);
                }
                else
                {
                    map.put(word, 1);
                }
            }
        }
        reader.close();

        return map;
    }

    public static String findMostUsedWord(Map<String, Integer> map)
    {
        String mostUsed = null;
        int wordCount = 0;

        for (String word : map.keySet())
        {
            if (map.get(word) > wordCount)
            {
                mostUsed = word;
                wordCount = map.get(word);
            }
        }

        return mostUsed;
    }

    public static void main(String[] args) throws IOException
    {
        Map<String, Integer> map = new HashMap<>();
        FileReader fr = new FileReader("src/file.txt");
        map = countWords(fr);

        System.out.println(" Map Elements");
        System.out.println("\t" + map);

        String mostUsed = findMostUsedWord(map);

        System.out.println("Most used word : " + mostUsed);
    }
}