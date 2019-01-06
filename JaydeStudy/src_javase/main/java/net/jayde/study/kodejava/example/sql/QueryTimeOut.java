package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class QueryTimeOut {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();

            //
            // Sets the number of seconds the driver will wait for
            // a statement object to execute to the given number of
            // seconds. If the limit is exceeded, an SQLException
            // is thrown.
            //
            stmt.setQueryTimeout(60);

            //
            // Execute sql query
            //
            ResultSet rs = stmt.executeQuery("select * from products");

            while (rs.next()) {
                System.out.println("code: " + rs.getString("code")
                        + " ,product: " + rs.getString("name")
                        + " ,price: " + rs.getDouble("price")
                        + " ,qty: " + rs.getInt("qty"));
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
