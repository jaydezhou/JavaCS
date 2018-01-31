package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class ResultSetExample {
    public static void main(String[] args) throws Exception {
        Connection connection = getConnection();
        try {
            String query = "SELECT id, title, publisher, year, price FROM books";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Read values using column name
                String id = rs.getString("id");
                String title = rs.getString("title");
                String publisher = rs.getString("publisher");

                // Read values using column index
                int year = rs.getInt(4);
                float price = rs.getFloat(5);

                System.out.printf("%s. %s, %s, %d, %f\n", id, title, publisher, year, price);
            }
        } finally {
            closeConnection(connection);
        }
    }

    private static Connection getConnection() throws Exception {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "");
        return connection;
    }

    private static void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
