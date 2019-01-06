package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class TableNameExample {
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
            String query = "SELECT a.id, a.username, a.country_id, b.country_name "
                    + "FROM users a "
                    + "LEFT JOIN countries b "
                    + "ON a.country_id = b.id";
            ResultSet resultSet = statement.executeQuery(query);

            //
            // Here we have a query that use multiple table, we then want to
            // know to which table a column is belong to. In the ResultSetMetaData
            // there is a getTableName() method that can do this functionality.
            //
            ResultSetMetaData metadata = resultSet.getMetaData();

            String tableName = metadata.getTableName(1);
            System.out.println("Table name of column 'id' = " + tableName);

            tableName = metadata.getTableName(4);
            System.out.println("Table name of column 'country name' = " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
