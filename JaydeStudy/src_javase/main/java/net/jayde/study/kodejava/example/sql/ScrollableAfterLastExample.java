package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class ScrollableAfterLastExample {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "root", "");

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            //
            // Let's jump to the last records. The afterLast() method will position
            // the cursor after the last record.
            //
            resultSet.afterLast();

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
