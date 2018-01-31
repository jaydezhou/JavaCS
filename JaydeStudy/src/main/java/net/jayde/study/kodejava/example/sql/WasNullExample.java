package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class WasNullExample {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = getConnection();

            //
            // Prepares a call to the sored procedure
            //
            String query = "call GET_PRODUCT_BY_PRICE2(?, ?)";
            CallableStatement cb = connection.prepareCall(query);

            //
            // Sets the input parameter
            //
            cb.setDouble(1, 150000d);

            //
            // Registers the OUT parameter
            //
            cb.registerOutParameter(2, Types.VARCHAR);

            //
            // Executes the query
            //
            cb.executeQuery();

            //
            // Gets the OUT parameter value
            //
            cb.getString(2);

            //
            // Checks if the last OUT parameter has value of SQL NULL.
            // This method should be called only after calling a
            // getter method; otherwise, there is no value to use in
            // determining whether it is null or not.
            //
            if (cb.wasNull()) {
                System.out.println("Product has an SQL NULL value");
            } else {
                System.out.println("Product: " + cb.getString(2));
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
