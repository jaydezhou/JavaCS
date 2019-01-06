package net.jayde.study.kodejava.example.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverInfo {
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

            int majorVersion = metadata.getDatabaseMajorVersion();
            System.out.println("majorVersion = " + majorVersion);

            int minorVersion = metadata.getDatabaseMinorVersion();
            System.out.println("minorVersion = " + minorVersion);

            String productName = metadata.getDatabaseProductName();
            System.out.println("productName = " + productName);

            String productVersion = metadata.getDatabaseProductVersion();
            System.out.println("productVersion = " + productVersion);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
