import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Challenge9
{
    public static void writeToFile(String newLine, File file) throws IOException
    {
        try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.write(newLine);
            bw.newLine();
        }
    }
    public static void main(String[] args) throws IOException
    {
        File file = new File("Challenge9.csv");

        Row row1 = new Row(11, 23);

        writeToFile("First name,Last name,Reason,Department,Date of the visit", file);
        writeToFile(row1.getRow(), file);
    }
}

class Row
{
    private FirstName firstName;
    private LastName lastName;
    private Reason reason;
    private Department department;
    private Date date;

    Row(int month, int year)
    {
        this.firstName = new FirstName();
        this.lastName = new LastName();
        this.reason = new Reason();
        this.department = (Objects.equals(reason.getReason(), "Appointment")) ? new Department() : null;
        this.date = new Date(month, year);
    }

    public String getRow()
    {
        String firstName = this.firstName.getFirstName();
        String lastName = this.lastName.getLastName();
        String reason = this.reason.getReason();
        String department = (this.department != null) ? this.department.getDepartment() : "";
        String date = this.date.getDate();

        return String.join(",", firstName, lastName, reason, department, date);
    }
}

abstract class Data
{
    public String getRandom(String[] arr)
    {
        int max = arr.length;
        int rand = (int) (Math.random() * max);
        return arr[rand];
    }
}

class FirstName extends Data
{
    private final String[] firstNames = {"Emma", "Liam", "Olivia", "Noah", "Ava", "Isabella", "Sophia", "Jackson", "Mia", "Lucas",
            "Oliver", "Aiden", "Charlotte", "Harper", "Evelyn", "Abigail", "Amelia", "Elijah", "Ethan", "Aria",
            "Mason", "Logan", "Grace", "Carter", "Lily", "Chloe", "Madison", "Avery", "Benjamin", "Lillian"};
    private String firstName;
    FirstName()
    {
        this.firstName = getRandom(firstNames);
    }

    public String getFirstName()
    {
        return this.firstName;
    }
}

class LastName extends Data
{
    private final String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson",
            "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King",
            "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Nelson", "Hall", "Turner"};

    private String lastName;

    LastName()
    {
        this.lastName = getRandom(lastNames);
    }

    public String getLastName()
    {
        return this.lastName;
    }
}

class Reason extends Data
{
    private final String[] reasons = {"Appointment", "Visit"};
    private String reason;

    Reason()
    {
        this.reason = getRandom(reasons);
    }

    public String getReason()
    {
        return this.reason;
    }
}

class Department extends Data
{
    private final String[] departments = {"Cardiology", "Radiology", "Pediatrics", "Geriatrics", "Pulmonology"};
    private String department;

    Department()
    {
        this.department = getRandom(departments);
    }

    public String getDepartment()
    {
        return this.department;
    }
}

class Date extends Data
{
    public String Date;

    Date(int month, int year)
    {
        this.Date = String.valueOf(getRandom()) + "-" + String.valueOf(month) + "-" + String.valueOf(year);
    }

    public int getRandom()
    {
        return (int) (Math.random() * 30 + 1);
    }

    public String getDate()
    {
        return this.Date;
    }
}