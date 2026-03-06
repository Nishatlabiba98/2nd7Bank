package MicroBank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
//create class account with name and balance
//
class Account {
    double balance;
    public Account(double balance) {
        this.balance = balance; // sets balance= whatever balance is passed in the concstructor
    }

    public void deposit(double amount) {
        if (amount < 0) {
            System.out.println("Invalid deposit amount");
        } else {
            this.balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > this.balance) {
            System.out.println("Insufficient funds");
        } else {
            this.balance -= amount;
        }
    }

    public void displayBalance() {
        System.out.println("Current Balance: " + this.balance);
    }
}


class Transaction {
    String type;
    double amount;

    public Transaction(String type, double amount) {

        this.type = type;
        this.amount = amount;
    }
}

//CREATED BANK CLASS AND added add account, display account, withdraw and deposit

class ReadData {
    public static ArrayList<Transaction> readData(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            ArrayList<Transaction> data = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if(parts.length < 3) {
                    System.out.println("Invalid transaction format: " + line);
                    continue;
                }

                String type = parts[1].trim();
                double amount = Double.parseDouble(parts[2].trim());
                data.add(new Transaction(type, amount));
            }
            scanner.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
    }
}
public class Main {



    public static void main(String[] args) {
        Account account = new Account(0.0);

        ArrayList<Transaction> transactions = ReadData.readData("./src/MicroBank/input.data");
        if (transactions == null) {
            System.out.println("No transactions to process.");
            return;
        }
        for (Transaction transaction : transactions) {
            if (transaction.type.equalsIgnoreCase("deposit")) {
                account.deposit( transaction.amount);
            } else if (transaction.type.equalsIgnoreCase("withdrawal")) {
                account.withdraw( transaction.amount);
            }
        }
        account.displayBalance();
    }


}