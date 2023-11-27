import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// class to store the different reports and data of one hospital
public class Hospital
{
    private List<HospitalFile> popFile = new ArrayList<>();;
    private List<DailyReport> dailyReport = new ArrayList<>();;
    private List<MonthlyReport> monthlyReport = new ArrayList<>();;

    Hospital(File file) throws IOException
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
            this.popFile.add(newFile);
        }
    }

    // function to call to create a report per day for one given month
    public void reportPerDay(int month) throws IOException
    {
        DailyReport newReport = new DailyReport("DailyReport_" + month + ".csv", month);

        for (HospitalFile file : this.popFile)
        {
            String[] data = file.getDate().split("-");
            int fileMonth = (data[1] != null) ? Integer.parseInt(data[1]) : 0;
            if (fileMonth == month)
            {
                newReport.addFile(file);
            }
        }

        this.dailyReport.add(newReport);
    }

    // function to call to read the daily report of a given month
    public void readDailyReport(int month)
    {
        for (DailyReport report : this.dailyReport)
        {
            if (report.isCorrectMonth(month))
            {
                report.readFiles();
            }
        }
    }

    // function to call to create a report per month for one given year
    public void reportPerMonth(int year) throws IOException
    {
        MonthlyReport newReport = new MonthlyReport("MonthlyReport_" + year + ".csv", year);

        for (HospitalFile file : this.popFile)
        {
            String[] data = file.getDate().split("-");
            int fileYear = (data[2] != null) ? Integer.parseInt(data[2]) : 0;
            if (fileYear == year)
            {
                newReport.addFile(file);
            }
        }

        this.monthlyReport.add(newReport);
    }

    // function to call to read the monthly report of a given year
    public void readMonthlyReport(int year)
    {
        for (MonthlyReport report : this.monthlyReport)
        {
            if (report.isCorrectYear(year))
            {
                report.readFiles();
            }
        }
    }
}