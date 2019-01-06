package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class SupportUpdatetableResultSet {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/kodejava";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            DatabaseMetaData metadata = connection.getMetaData();

            boolean updatable = metadata.supportsResultSetConcurrency(
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            System.out.println("Updatable ResultSet supported = " + updatable);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
