package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class ScrollableGetRowExample {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "root", "");

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                String productCode = resultSet.getString("product_code");

                //
                // By calling the getRow() method of the result set we know what is the
                // current row in the result set that we are reading the data from.
                //
                int row = resultSet.getRow();

                System.out.println(row + ". " + productCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
