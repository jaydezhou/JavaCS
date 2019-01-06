package net.jayde.study.kodejava.example.sql;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcCompliant {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/kodejavadb";
            Driver driver = DriverManager.getDriver(url);

            //
            // Check if the driver is a genuine JDBC compliant driver.
            //
            if (driver.jdbcCompliant()) {
                System.out.println("A genuine JDBC compliant driver");
            } else {
                System.out.println("Not a genuine JDBC compliant driver");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
