import java.sql.*;
import java.util.Scanner;

public class SearchEmployee {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        int empid = input.nextInt();

        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employeedata3",
                "root",
                "YOUR_PASSWORD"
            );

            String sql = "SELECT * FROM employees WHERE empid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, empid);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Employee Found:");
                System.out.println("ID: " + rs.getInt("empid"));
                System.out.println("First Name: " + rs.getString("Fname"));
                System.out.println("Last Name: " + rs.getString("Lname"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Salary: " + rs.getDouble("Salary"));
                System.out.println("SSN: " + rs.getString("SSN"));
            } else {
                System.out.println("Employee not found.");
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        input.close();
    }
}
