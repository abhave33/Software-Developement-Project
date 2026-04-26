import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Reports {

    public static void main(String[] args) {
        Scanner UserInput = new Scanner(System.in);

        System.out.println("Reports Menu");
        System.out.println("1. Total Pay by Job Title");
        System.out.println("2. Total Pay by Division");
        System.out.println("3. Employee Pay History");
        System.out.print("Enter your choice: ");

        int MenuChoice = UserInput.nextInt();

        if (MenuChoice == 1) {
            PayByJobTitle();
        } else if (MenuChoice == 2) {
            PayByDivision();
        } else if (MenuChoice == 3) {
            System.out.print("Enter Employee ID: ");
            int EmployeeID = UserInput.nextInt();
            PayHistory(EmployeeID);
        } else {
            System.out.println("Invalid choice.");
        }

        UserInput.close();
    }

    public static void PayByJobTitle() {
        String ReportSQL = "SELECT employees.jobTitle, SUM(pay_statement.grossPay) AS totalPay " +
                           "FROM employees " +
                           "JOIN pay_statement ON employees.empid = pay_statement.empid " +
                           "GROUP BY employees.jobTitle";

        try {
            Connection DatabaseConnection = DBConnection.getConnection();
            PreparedStatement PreparedStatementObject = DatabaseConnection.prepareStatement(ReportSQL);
            ResultSet ReportResults = PreparedStatementObject.executeQuery();

            while (ReportResults.next()) {
                System.out.println("Job Title: " + ReportResults.getString("jobTitle"));
                System.out.println("Total Pay: $" + ReportResults.getDouble("totalPay"));
                System.out.println();
            }

            DatabaseConnection.close();
        } catch (Exception ErrorMessage) {
            System.out.println("Error: " + ErrorMessage.getMessage());
        }
    }

    public static void PayByDivision() {
        String ReportSQL = "SELECT employees.division, SUM(pay_statement.grossPay) AS totalPay " +
                           "FROM employees " +
                           "JOIN pay_statement ON employees.empid = pay_statement.empid " +
                           "GROUP BY employees.division";

        try {
            Connection DatabaseConnection = DBConnection.getConnection();
            PreparedStatement PreparedStatementObject = DatabaseConnection.prepareStatement(ReportSQL);
            ResultSet ReportResults = PreparedStatementObject.executeQuery();

            while (ReportResults.next()) {
                System.out.println("Division: " + ReportResults.getString("division"));
                System.out.println("Total Pay: $" + ReportResults.getDouble("totalPay"));
                System.out.println();
            }

            DatabaseConnection.close();
        } catch (Exception ErrorMessage) {
            System.out.println("Error: " + ErrorMessage.getMessage());
        }
    }

    public static void PayHistory(int EmployeeID) {
        String ReportSQL = "SELECT employees.empid, employees.Fname, employees.Lname, " +
                           "pay_statement.payDate, pay_statement.grossPay, " +
                           "pay_statement.taxes, pay_statement.netPay " +
                           "FROM employees " +
                           "JOIN pay_statement ON employees.empid = pay_statement.empid " +
                           "WHERE employees.empid = ?";

        try {
            Connection DatabaseConnection = DBConnection.getConnection();
            PreparedStatement PreparedStatementObject = DatabaseConnection.prepareStatement(ReportSQL);
            PreparedStatementObject.setInt(1, EmployeeID);

            ResultSet ReportResults = PreparedStatementObject.executeQuery();

            while (ReportResults.next()) {
                System.out.println("Employee ID: " + ReportResults.getInt("empid"));
                System.out.println("Name: " + ReportResults.getString("Fname") + " " + ReportResults.getString("Lname"));
                System.out.println("Pay Date: " + ReportResults.getDate("payDate"));
                System.out.println("Gross Pay: $" + ReportResults.getDouble("grossPay"));
                System.out.println("Taxes: $" + ReportResults.getDouble("taxes"));
                System.out.println("Net Pay: $" + ReportResults.getDouble("netPay"));
                System.out.println();
            }

            DatabaseConnection.close();
        } catch (Exception ErrorMessage) {
            System.out.println("Error: " + ErrorMessage.getMessage());
        }
    }
}
