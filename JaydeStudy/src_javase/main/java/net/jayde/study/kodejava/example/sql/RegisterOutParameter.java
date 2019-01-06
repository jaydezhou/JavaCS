package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class RegisterOutParameter {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = getConnection();

            //
            // Creates a CallableStatement for executing the
            // stored procedure
            //
            String query = "call GET_DETAIL_BY_PRODUCT(?, ?, ?, ?)";
            CallableStatement cb = connection.prepareCall(query);

            // 
            // Sets the input parameter
            //
            cb.setString(1, "bag");

            //
            // Registers the out parameters
            //
            cb.registerOutParameter(2, Types.VARCHAR);
            cb.registerOutParameter(3, Types.DECIMAL);
            cb.registerOutParameter(4, Types.INTEGER);

            //
            // Executes the query
            //
            cb.executeQuery();

            //
            // Gets the query result output
            //
            System.out.println("Code    : " + cb.getString(2));
            System.out.println("Price   : " + cb.getBigDecimal(3));
            System.out.println("Quantity: " + cb.getInt(4));

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
