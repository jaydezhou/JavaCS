package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class SqlLimitExample {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = getConnection();

            //
            // Create PreparedStatement to get all data from database.
            //
            String query = "select count(*) from products";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet result = ps.executeQuery();

            int total = 0;
            while (result.next()) {
                total = result.getInt(1);
            }

            System.out.println("Total number of data in database: " +
                    total + "\n");

            //
            // Create PreparedStatement to the first 5 records only.
            //
            query = "select * from products limit 5";
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();

            System.out.println("Result fetched with specified limit 5");
            System.out.println("====================================");
            while (result.next()) {
                System.out.println("id:" + result.getInt("id") +
                        ", code:" + result.getString("code") +
                        ", name:" + result.getString("name") +
                        ", price:" + result.getString("price") +
                        ", qty:" + result.getString("qty"));
            }

            //
            // Create PreparedStatement to get data from the 4th
            // record (remember the first record is 0) and limited
            // to 3 records only.
            //
            query = "select * from products limit 3, 3";
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();

            System.out.println("\nResult fetched with specified limit 3, 3");
            System.out.println("====================================");
            while (result.next()) {
                System.out.println("id:" + result.getInt("id") +
                        ", code:" + result.getString("code") +
                        ", name:" + result.getString("name") +
                        ", price:" + result.getString("price") +
                        ", qty:" + result.getString("qty"));
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
        String url = "jdbc:mysql://localhost/kodejavadb";
        return DriverManager.getConnection(url, "root", "");
    }

    /**
     * Close a connection to database.
     *
     * @param connection a connection to be closed.
     * @throws SQLException when an exception occurs.
     */
    private static void closeConnection(Connection connection)
            throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
