import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

// class to store the data of one row in csv
public class HospitalFile
{
    private String date;
    private int cardiology;
    private int radiology;
    private int maternity;
    private int emergency;
    private int visitors;

    HospitalFile(String date, int cardiology, int radiology, int maternity, int emergency, int visitors)
    {
        this.date = date;
        this.cardiology = cardiology;
        this.radiology = radiology;
        this.maternity = maternity;
        this.emergency = emergency;
        this.visitors = visitors;
    }

    public String getDate()
    {
        return this.date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public int getCardiology()
    {
        return this.cardiology;
    }

    public void setCardiology(int cardiology)
    {
        this.cardiology = cardiology;
    }

    public int getRadiology()
    {
        return this.radiology;
    }

    public void setRadiology(int radiology)
    {
        this.radiology = radiology;
    }

    public int getMaternity()
    {
        return this.maternity;
    }

    public void setMaternity(int maternity)
    {
        this.maternity = maternity;
    }

    public int getEmergency()
    {
        return this.emergency;
    }

    public void setEmergency(int emergency)
    {
        this.emergency = emergency;
    }

    public int getVisitors()
    {
        return this.visitors;
    }

    public void setVisitors(int visitors)
    {
        this.visitors = visitors;
    }

    // get string of row to add to csv file
    public String getString()
    {
        return String.join(",", this.date, Integer.toString(this.cardiology), Integer.toString(this.radiology), Integer.toString(this.maternity), Integer.toString(this.emergency), Integer.toString(this.visitors));
    }

    // function to compare if file with same date is already existing
    public boolean compareFiles(List<HospitalFile> files)
    {
        for (HospitalFile file : files)
        {
            if (Objects.equals(file.date, this.date)) return false;
        }
        return true;
    }
}

// class to store the daily reports of one month
class DailyReport
{
    private List<HospitalFile> dailyFiles = new ArrayList<>();
    private File dailyFile;
    private int month;

    DailyReport(String fileName, int month) throws IOException
    {
        File dailyFile = new File(fileName);
        this.dailyFile = dailyFile;
        if (!dailyFile.createNewFile()) setFiles(dailyFile);
        else writeToFile("date,cardiology,radiology,maternity,emergency,visitors");
        this.month = month;
    }

    // function to write a line to csv file
    public void writeToFile(String newLine) throws IOException
    {
        try (FileWriter fw = new FileWriter(this.dailyFile, true); BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.write(newLine);
            bw.newLine();
        }
    }

    // function to add a file to list and call the write function
    public void addFile(HospitalFile newFile) throws IOException
    {
        if (this.dailyFiles.isEmpty() || newFile.compareFiles(this.dailyFiles))
        {
            this.dailyFiles.add(newFile);
            writeToFile(newFile.getString());
        }
    }

    // function to read and print all the files
    public void readFiles()
    {
        for (HospitalFile file : this.dailyFiles)
        {
            System.out.println();
            System.out.print("date: " + file.getDate());
            System.out.print("cardiology: " + file.getCardiology());
            System.out.print("radiology: " + file.getRadiology());
            System.out.print("maternity: " + file.getMaternity());
            System.out.print("emergency: " + file.getEmergency());
            System.out.print("visitors: " + file.getVisitors());
        }
    }

    // set files if csv file already exist
    void setFiles(File file) throws IOException
    {
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();

        while (scanner.hasNextLine())
        {
            line = scanner.nextLine();
            String[] data = line.split(",");

            String date = (data[0] != null) ? data[0] : "";
            int cardiology = (data[1] != null) ? Integer.parseInt(data[1]) : 0;
            int radiology = (data[2] != null) ? Integer.parseInt(data[2]) : 0;
            int maternity = (data[3] != null) ? Integer.parseInt(data[3]) : 0;
            int emergency = (data[4] != null) ? Integer.parseInt(data[4]) : 0;
            int visitors = (data[5] != null) ? Integer.parseInt(data[5]) : 0;

            HospitalFile newFile = new HospitalFile(date, cardiology, radiology, maternity, emergency, visitors);
            this.dailyFiles.add(newFile);
        }
    }

    // find if the given month is the correct one
    public boolean isCorrectMonth(int month)
    {
        return month == this.month;
    }
}

// class to store the monthly reports of one year
class MonthlyReport
{
    private List<HospitalFile> monthlyFiles = new ArrayList<>();
    private File monthlyFile;

    private int year;

    MonthlyReport(String fileName, int year) throws IOException
    {
        File monthlyFile = new File(fileName);
        this.monthlyFile = monthlyFile;
        if (!monthlyFile.createNewFile()) setFiles(monthlyFile);
        else writeToFile("month,cardiology,radiology,maternity,emergency,visitors");
        this.year = year;
    }

    // function to write a line to csv file
    public void writeToFile(String newLine) throws IOException
    {
        try (FileWriter fw = new FileWriter(this.monthlyFile, true); BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.write(newLine);
            bw.newLine();
        }
    }

    // function to add a file to list and call the write function
    public void addFile(HospitalFile newFile) throws IOException
    {
        String[] newFileDate = newFile.getDate().split("-");
        newFile.setDate(newFileDate[1]);
        int newFileMonth = Integer.parseInt(newFileDate[1]);

        if (!this.monthlyFiles.isEmpty() && !newFile.compareFiles(this.monthlyFiles)) addToExistingMonth(newFileMonth, newFile);
        else
        {
            writeToFile(newFile.getString());
            this.monthlyFiles.add(newFile);
        }
    }

    // function to read and print all the files
    public void readFiles()
    {
        for (HospitalFile file : this.monthlyFiles)
        {
            System.out.println();
            System.out.print("date: " + file.getDate());
            System.out.print("cardiology: " + file.getCardiology());
            System.out.print("radiology: " + file.getRadiology());
            System.out.print("maternity: " + file.getMaternity());
            System.out.print("emergency: " + file.getEmergency());
            System.out.print("visitors: " + file.getVisitors());
        }
    }

    // set files if csv file already exist
    void setFiles(File file) throws IOException
    {
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();

        while (scanner.hasNextLine())
        {
            line = scanner.nextLine();
            String[] data = line.split(",");

            String date = (data[0] != null) ? data[0] : "";
            int cardiology = (data[1] != null) ? Integer.parseInt(data[1]) : 0;
            int radiology = (data[2] != null) ? Integer.parseInt(data[2]) : 0;
            int maternity = (data[3] != null) ? Integer.parseInt(data[3]) : 0;
            int emergency = (data[4] != null) ? Integer.parseInt(data[4]) : 0;
            int visitors = (data[5] != null) ? Integer.parseInt(data[5]) : 0;

            HospitalFile newFile = new HospitalFile(date, cardiology, radiology, maternity, emergency, visitors);
            monthlyFiles.add(newFile);
        }
    }

    // function to add data to an already existing month and rewrite the csv file with updated data
    public void addToExistingMonth(int month, HospitalFile newFile) throws IOException
    {
        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("month,cardiology,radiology,maternity,emergency,visitors");
        for (HospitalFile file : monthlyFiles)
        {
            if (Integer.parseInt(file.getDate()) == month)
            {
                file.setCardiology(file.getCardiology() + newFile.getCardiology());
                file.setRadiology(file.getRadiology() + newFile.getRadiology());
                file.setMaternity(file.getMaternity() + newFile.getMaternity());
                file.setEmergency(file.getEmergency() + newFile.getEmergency());
                file.setVisitors(file.getVisitors() + newFile.getVisitors());
            }
            myWriter.write(file.getString());
        }

        myWriter.close();
    }

    // find if the given year is the correct one
    public boolean isCorrectYear(int year)
    {
        return year == this.year;
    }
}