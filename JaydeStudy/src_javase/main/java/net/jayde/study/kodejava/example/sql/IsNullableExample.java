package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class IsNullableExample {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/testdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, username FROM users");

            //
            // The ResultSetMetaData is where all metadata related information
            // for a result set is stored.
            //
            ResultSetMetaData metadata = resultSet.getMetaData();
            int nullability = metadata.isNullable(1);

            //
            // Check the nullability status of a column (ID)
            //
            if (nullability == ResultSetMetaData.columnNullable) {
                System.out.println("Columns ID can have a null value");
            } else if (nullability == ResultSetMetaData.columnNoNulls) {
                System.out.println("Columns ID does not allowed to have a null value");
            } else if (nullability == ResultSetMetaData.columnNullableUnknown) {
                System.out.println("Nullability unknown");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
