package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class ScrollableExample {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/testdb";
            connection = DriverManager.getConnection(url, "root", "");

            //
            // Since JDBC 2.0 (JDK 1.2) a scrollable ResultSet was 
            // introduced to the java.sql API family. Using this 
            // ResultSet enables us to navigate the resultset in 
            // forward or backward way.
            //
            // To enable the scrollable ResultSet we need to create 
            // a statement object by defining the ResultSet type 
            // (ResultSet.TYPE_SCROLL_SENSITIVE, 
            // ResultSet.TYPE_SCROLL_INSENSITIVE). If you define the 
            // ResultSet type to ResultSet.TYPE_FORWARD_ONLY then you 
            // get a regular ResultSet where you can move forward 
            // only as in JDBC 1.0
            //
            Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            //
            // This result set is a scrollable result set
            //
            String query = "SELECT * FROM products";
            ResultSet resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
