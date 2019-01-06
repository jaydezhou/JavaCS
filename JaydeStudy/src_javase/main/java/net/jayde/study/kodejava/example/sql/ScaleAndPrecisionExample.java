package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class ScaleAndPrecisionExample {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/testdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String query = "SELECT stock_id, name, price FROM stocks";
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metadata = resultSet.getMetaData();
            int precision = metadata.getPrecision(3);
            int scale = metadata.getScale(3);

            System.out.println("Precision: " + precision);
            System.out.println("Scale    : " + scale);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
