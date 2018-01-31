package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class SupportScrollableResultSet {
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

            boolean supportForwardOnly =
                    metadata.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            System.out.println("supportForwardOnly = " + supportForwardOnly);

            boolean supportScrollInsensitive =
                    metadata.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            System.out.println("supportScrollInsensitive = " + supportScrollInsensitive);

            boolean supportScrollSensitive =
                    metadata.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE);
            System.out.println("supportScrollSensitive = " + supportScrollSensitive);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
