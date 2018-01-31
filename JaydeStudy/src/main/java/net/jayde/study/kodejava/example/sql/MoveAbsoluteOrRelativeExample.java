package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class MoveAbsoluteOrRelativeExample {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "root", "");

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            //
            // Move to the second row
            //
            resultSet.absolute(2);
            System.out.println("You are now in: " + resultSet.getRow());

            //
            // Move 2 records forward from the current position (fourth row)
            //
            resultSet.relative(2);
            System.out.println("You are now in: " + resultSet.getRow());

            //
            // Move to the last row in the result set
            //
            resultSet.absolute(-1);
            System.out.println("You are now in: " + resultSet.getRow());

            //
            // Move 3 records backward from the current position (second row)
            //
            resultSet.relative(-3);
            System.out.println("You are now in: " + resultSet.getRow());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
