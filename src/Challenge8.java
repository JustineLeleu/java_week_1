import java.io.File;
import java.io.IOException;

public class Challenge8
{
    public static void main(String[] args) throws IOException
    {
        Hospital hospital = new Hospital(new File("src/hospital.csv"));

        hospital.reportPerDay(11);
        hospital.readDailyReport(11);

        hospital.reportPerMonth(23);
        hospital.readMonthlyReport(23);
    }
}