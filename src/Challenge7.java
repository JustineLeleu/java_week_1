import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Challenge7
{
    public static List<SVCFile> createFiles(File file)
    {
        List<SVCFile> files = new ArrayList<>();

        try(Scanner scanner = new Scanner(file))
        {
            String line = scanner.nextLine();

            while (scanner.hasNextLine())
            {
                line = scanner.nextLine();
                String[] data = line.split(",");

                String name = (data[0] != null) ? data[0] : "";
                int age = (data[1] != null) ? Integer.parseInt(data[1]) : 0;
                char gender = (data[2] != null && data[2].length() == 1) ? data[2].charAt(0) : 'N';

                SVCFile newFile = new SVCFile(name, age, gender);
                files.add(newFile);
            }
        }
        catch (IOException e)
        {
            System.out.println("error : " + e.getMessage());
        }

        return files;
    }

    public static void main(String[] args)
    {
        List<SVCFile> files = createFiles(new File("src/file.csv"));

        System.out.println(files.get(0).getName());
        System.out.println(files.get(0).getAge());
        System.out.println(files.get(0).getGender());

        files.get(1).setName("Chose");
        files.get(1).setAge(60);
        files.get(1).setGender('F');
        System.out.println(files.get(1).getName());
        System.out.println(files.get(1).getAge());
        System.out.println(files.get(1).getGender());
    }
}