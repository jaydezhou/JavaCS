package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class GettingTableListExample {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "kodejava";
            String password = "welcome";
            connection = DriverManager.getConnection(url, username, password);

            // Gets the metadata of the database
            DatabaseMetaData dbmd = connection.getMetaData();
            String[] types = {"TABLE"};

            ResultSet rs = dbmd.getTables(null, null, "%", types);
            while (rs.next()) {
                String tableCatalog = rs.getString(1);
                String tableSchema = rs.getString(2);
                String tableName = rs.getString(3);

                System.out.printf("%s - %s - %s%n", tableCatalog, tableSchema, tableName);
            }
        } catch (SQLException e) {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}
