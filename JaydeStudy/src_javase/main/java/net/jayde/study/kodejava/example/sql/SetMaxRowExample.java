package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class SetMaxRowExample {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();

            //
            // Executes an SQL query to get the total number of data
            // in products table.
            //
            String query = "select count(*) from products";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.println("Total number of Products: " + rs.getInt(1));
            }

            //
            // Set the maximum row of data that can be stored in the
            // ResultSet.
            //
            statement.setMaxRows(5);
            //
            // Executes an SQL query to retrieve data from Products
            // table.
            //
            query = "select id, code, name, price, qty from products";
            rs = statement.executeQuery(query);

            System.out.println("Data read after the MaxRows is set.");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + ", CODE: " + rs.getString("code")
                        + ", NAME: " + rs.getString("name")
                        + ", PRICE: " + rs.getBigDecimal("price")
                        + ", QTY: " + rs.getInt("qty"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * Get a connection to database.
     *
     * @return a connection to database.
     * @throws Exception when an exception occurs.
     */
    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/kodejavadb", "root", "");
    }

    /**
     * Close a connection to database.
     *
     * @param connection a connection to be closed.
     * @throws SQLException when an exception occurs.
     */
    private static void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
