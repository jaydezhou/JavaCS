package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class ScrollableMoveExample {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "root", "");

            //
            // In this example we first create a statement that allows us to go back and forth
            // in the result set object. First we'll iterate the result from beginning to the
            // end. 
            //
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                String productCode = resultSet.getString("product_code");
                String productName = resultSet.getString("product_name");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");

                System.out.println(productCode + "\t" + productName + "\t" + quantity + "\t" + price);
            }

            System.out.println("");

            //
            // Now the result set pointer is placed after the last record. With the previous
            // method of the result set we can now move the pointer backward to the beginning
            // of the result set.
            //            
            while (resultSet.previous()) {
                String productCode = resultSet.getString("product_code");
                String productName = resultSet.getString("product_name");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");

                System.out.println(productCode + "\t" + productName + "\t" + quantity + "\t" + price);
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
