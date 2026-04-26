import java.sql.*;
import java.util.Scanner;

public class SearchEmployee {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            int choice;

            do {
                System.out.println("\nEmployee Search Menu");
                System.out.println("1. Search by Employee ID");
                System.out.println("2. Search by First Name");
                System.out.println("3. Search by Last Name");
                System.out.println("4. Search by SSN");
                System.out.println("5. Update Employee Information");
                System.out.println("6. Insert New Employee");
                System.out.println("7. Delete Employee");
                System.out.println("8. Update Salary by Percentage Range");
                System.out.println("9. Exit");
                System.out.print("Enter your choice: ");

                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Employee ID: ");
                        int empid = input.nextInt();
                        input.nextLine();
                        searchEmployee("SELECT * FROM employees WHERE empid = ?", empid);
                        break;

                    case 2:
                        System.out.print("Enter First Name: ");
                        String firstName = input.nextLine();
                        searchEmployee("SELECT * FROM employees WHERE Fname = ?", firstName);
                        break;

                    case 3:
                        System.out.print("Enter Last Name: ");
                        String lastName = input.nextLine();
                        searchEmployee("SELECT * FROM employees WHERE Lname = ?", lastName);
                        break;

                    case 4:
                        System.out.print("Enter SSN: ");
                        String ssn = input.nextLine();
                        searchEmployee("SELECT * FROM employees WHERE SSN = ?", ssn);
                        break;

                    case 5:
                        updateEmployee(input);
                        break;

                    case 6:
                        insertEmployee(input);
                        break;
                    case 7:
                        deleteEmployee(input);
                        break;

                    case 8:
                        updateSalaryByRange(input);
                        break;

                    case 9:
                        System.out.println("Exiting program.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 9);
        }
    }

    private static void searchEmployee(String sql, Object value) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("Unable to connect to the database.");
                return;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                if (value instanceof Integer) {
                    stmt.setInt(1, (Integer) value);
                } else {
                    stmt.setString(1, value.toString());
                }

                try (ResultSet rs = stmt.executeQuery()) {
                    boolean found = false;

                    while (rs.next()) {
                        found = true;
                        System.out.println("Employee Found:");
                        System.out.println("empid: " + rs.getInt("empid"));
                        System.out.println("Fname: " + rs.getString("Fname"));
                        System.out.println("Lname: " + rs.getString("Lname"));
                        System.out.println("email: " + rs.getString("email"));
                        System.out.println("Salary: " + rs.getDouble("Salary"));
                        System.out.println("SSN: " + rs.getString("SSN"));
                        System.out.println();
                    }

                    if (!found) {
                        System.out.println("Employee not found.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    private static void updateEmployee(Scanner input) {
        System.out.print("Enter Employee ID to update: ");
        int empid = input.nextInt();
        input.nextLine();

        System.out.print("Enter new email: ");
        String email = input.nextLine();

        System.out.print("Enter new salary: ");
        double salary = input.nextDouble();
        input.nextLine();

        System.out.print("Enter new SSN: ");
        String ssn = input.nextLine();

        String sql = "UPDATE employees SET email = ?, Salary = ?, SSN = ? WHERE empid = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            if (conn == null) {
                System.out.println("Unable to connect to the database.");
                return;
            }

            stmt.setString(1, email);
            stmt.setDouble(2, salary);
            stmt.setString(3, ssn);
            stmt.setInt(4, empid);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Employee information updated successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    private static void insertEmployee(Scanner input) {
        System.out.print("Enter Employee ID: ");
        int empid = input.nextInt();
        input.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Enter Email: ");
        String email = input.nextLine();

        System.out.print("Enter Salary: ");
        double salary = input.nextDouble();
        input.nextLine();

        System.out.print("Enter SSN: ");
        String ssn = input.nextLine();

        String sql = "INSERT INTO employees (empid, Fname, Lname, email, Salary, SSN) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            if (conn == null) {
                System.out.println("Unable to connect to the database.");
                return;
            }

            stmt.setInt(1, empid);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setDouble(5, salary);
            stmt.setString(6, ssn);

            stmt.executeUpdate();
            System.out.println("New employee inserted successfully.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Employee ID already exists. Please use a different empid.");
        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    private static void deleteEmployee(Scanner input) {
        System.out.print("Enter Employee ID to delete: ");
        int empid = input.nextInt();
        input.nextLine();

        String sql = "DELETE FROM employees WHERE empid = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            if (conn == null) {
                System.out.println("Unable to connect to the database.");
                return;
            }

            stmt.setInt(1, empid);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }

        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    private static void updateSalaryByRange(Scanner input) {
        System.out.print("Enter minimum salary: ");
        double minSalary = input.nextDouble();

        System.out.print("Enter maximum salary: ");
        double maxSalary = input.nextDouble();

        System.out.print("Enter percentage increase: ");
        double percentIncrease = input.nextDouble();
        input.nextLine();

        String sql = "UPDATE employees " +
                     "SET Salary = Salary + (Salary * ? / 100) " +
                     "WHERE Salary >= ? AND Salary < ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            if (conn == null) {
                System.out.println("Unable to connect to the database.");
                return;
            }

            stmt.setDouble(1, percentIncrease);
            stmt.setDouble(2, minSalary);
            stmt.setDouble(3, maxSalary);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println(rowsUpdated + " employee salary record(s) updated successfully.");

        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}
