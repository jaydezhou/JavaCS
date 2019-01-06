package net.jayde.study.kodejava.example.fundamental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ThrowsExample {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            //
            // Might throws ClassNotFoundException or SQLException
            // that's why we should catch them.
            //
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Finally is always executed");
            System.out.println("Close connection");
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Sql exception caught");
            }
        }
    }

    /**
     * Get database connection.
     *
     * @return Connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/kodejavadb", "root", "");
    }
}
