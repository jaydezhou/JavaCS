package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class GetGeneratedKeyExample {
    private static final String URL = "jdbc:mysql://localhost/testdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //
            // The orders table have an ID column which value will be auto generated
            // by database. When inserting a new record a new id for the primary key
            // will be generated and we will get the generated key so we can used it
            // in another process. For instance if we have a master detail tables
            // where the details table required an id from the master table.
            //
            String insert = "INSERT INTO orders (username, order_date) VALUES ('foobar', '2007-12-13')";
            Statement stmt = connection.createStatement();

            //
            // When executing the statement we can pass the Statement.RETURN_GENERATED_KEYS
            // so that we can later extract the generated key from the result set object
            // returned by this method.
            //
            stmt.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);

            ResultSet keys = stmt.getGeneratedKeys();
            int lastKey = 1;
            while (keys.next()) {
                lastKey = keys.getInt(1);
            }

            System.out.println("Last Key: " + lastKey);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}
