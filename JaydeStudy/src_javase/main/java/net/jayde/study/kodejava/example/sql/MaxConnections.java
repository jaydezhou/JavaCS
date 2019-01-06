package net.jayde.study.kodejava.example.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaxConnections {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = getConnection();

            //
            // Get database meta data.
            //
            DatabaseMetaData metaData = connection.getMetaData();

            //
            // Retrieves the maximum number of concurrent
            // connections to this database that are possible.
            // A result of zero means that there is no limit or
            // the limit is not known.
            //
            int max = metaData.getMaxConnections();
            System.out.println("Max concurrent connections allowed: " + max);
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
