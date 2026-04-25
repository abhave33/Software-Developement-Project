import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employeedata3",
                "root",
                "YOUR_PASSWORD"
            );

            System.out.println("Database connected successfully.");

        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
        }

        return conn;
    }
}
