import java.sql.*;
public class DBConnector {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/MetroLiteraryHaven";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD =  "root";

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
        return null;
    }
}
