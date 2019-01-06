package net.jayde.study.kodejava.example.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTableExample {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            // This is the JDBC driver class for Oracle database
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // We use an Oracle express database for this example
            String url = "jdbc:oracle:thin:@localhost:1521:xe";

            // Define the username and password for connection to
            // our database.
            String username = "kodejava";
            String password = "welcome";

            // To delete a table from database we use the DROP TABLE
            // command and specify the table name to be dropped
            String sql = "DROP TABLE books";

            // Connect to database
            connection = DriverManager.getConnection(url, username, password);
            // Create a statement
            Statement statement = connection.createStatement();
            // Execute the statement to delete the table
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}
