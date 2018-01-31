package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class IsAutoIncrementExample {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/testdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            //
            // As the usual ritual, load the driver class and get connection
            // from database.
            //
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, username FROM users");

            //
            // The ResultSetMetaData is where all metadata related information
            // for a result set is stored.
            //
            ResultSetMetaData metadata = resultSet.getMetaData();
            if (metadata.isAutoIncrement(1)) {
                System.out.println("Column ID is an auto-increment column");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
