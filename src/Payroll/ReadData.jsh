import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;



class Timecard {
    String Name;
    double HoursWorked;
    double HourlyRate;

    public Timecard(String name, double hoursWorked, double hourlyRate) {
        this.Name = name;
        this.HoursWorked = hoursWorked;
        this.HourlyRate = hourlyRate;
    }
}

public class ReadData {
    public static ArrayList<Timecard> readData(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            ArrayList<Timecard> data = new ArrayList<>();

            // Skip header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0].trim();
                double hoursWorked = Double.parseDouble(parts[1].trim());
                double hourlyRate = Double.parseDouble(parts[2].trim());
                data.add(new Timecard(name, hoursWorked, hourlyRate));
            }
            scanner.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
    }

    public static void main(String[] args) {
        double taxRate = 0.2;
        ArrayList<Timecard> input_data = ReadData.readData("./src/Payroll/input.data");
        if (input_data == null) {
            System.out.println("No data to process.");
            return;
        }
        for (Timecard timecard : input_data) {
            double grossPay = timecard.HoursWorked * timecard.HourlyRate;
            double tax = grossPay * taxRate;
            double netPay = grossPay - tax;
            System.out.println("Name: " + timecard.Name + "\n" + ", Gross Pay:  " + grossPay + "\n" + ", Tax:  " + tax + "\n" + ", NetPay:  " + netPay);

        }

    }
}




