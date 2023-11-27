import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Challenge6
{
    public static Map<Character, Integer> countLetters(FileReader fr) throws IOException
    {
        Map<Character, Integer> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(fr);
        String line;

        while ((line = reader.readLine()) != null)
        {
            String[] words = line.split(" ");
            for (String value : words)
            {
                String word = value.toLowerCase();
                char[] letters = word.toCharArray();
                for (char letter : letters)
                {
                    if (map.containsKey(letter))
                    {
                        int count = map.get(letter) + 1;
                        map.put(letter, count);
                    }
                    else
                    {
                        map.put(letter, 1);
                    }
                }
            }
        }
        reader.close();

        return map;
    }

    public static char findMostUsedWord(Map<Character, Integer> map)
    {
        Character mostUsed = null;
        int letterCount = 0;

        for (char letter : map.keySet())
        {
            if (map.get(letter) > letterCount)
            {
                mostUsed = letter;
                letterCount = map.get(letter);
            }
        }

        return mostUsed;
    }

    public static void main(String[] args) throws IOException
    {
        Map<Character, Integer> map = new HashMap<>();
        FileReader fr = new FileReader("src/file.txt");
        map = countLetters(fr);

        System.out.println(" Map Elements");
        System.out.println("\t" + map);

        char mostUsed = findMostUsedWord(map);

        System.out.println("Most used word : " + mostUsed);
    }
}