import java.sql.*;

public class InsertDemo {
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    static final String USERNAME = "root";
    static final String PASSWORD = "password";

    public static void main(String[] args) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), email VARCHAR(100))";
        String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            try (PreparedStatement createStmt = conn.prepareStatement(createTableSQL)) {
                createStmt.execute();
            }
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, "John");
                pstmt.setString(2, "john@example.com");
                pstmt.executeUpdate();
                pstmt.setString(1, "Jane");
                pstmt.setString(2, "jane@example.com");
                pstmt.executeUpdate();
            }
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
