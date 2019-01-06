package net.jayde.study.kodejava.example.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchExample {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "root", "");

            //
            // Turn of the auto-commit mode
            //
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();

            //
            // And some batch to insert some product information into the product table
            //
            statement.addBatch("INSERT INTO products (product_code, product_name, quantity, price) VALUE ('P0000006', 'Championship Manager', 10.99, 20)");
            statement.addBatch("INSERT INTO products (product_code, product_name, quantity, price) VALUE ('P0000007', 'Transport Tycoon Deluxe', 15.99, 19)");
            statement.addBatch("INSERT INTO products (product_code, product_name, quantity, price) VALUE ('P0000008', 'Rollercoaster Tycoon 3', 5.99, 25)");
            statement.addBatch("INSERT INTO products (product_code, product_name, quantity, price) VALUE ('P0000009', 'Pro Evolution Soccer', 8.99, 50)");

            //
            // To execute a batch command we must call the executeBatch() method.
            //
            int[] updateCounts = statement.executeBatch();

            //
            // Commit our transcation
            //
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
