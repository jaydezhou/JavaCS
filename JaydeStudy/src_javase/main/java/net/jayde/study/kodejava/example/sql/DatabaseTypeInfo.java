package net.jayde.study.kodejava.example.sql;

import java.sql.*;


public class DatabaseTypeInfo {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/kodejava";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            DatabaseMetaData metadata = connection.getMetaData();
            resultSet = metadata.getTypeInfo();
            while (resultSet.next()) {
                String typeName = resultSet.getString("TYPE_NAME");
                System.out.println("Type Name = " + typeName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
