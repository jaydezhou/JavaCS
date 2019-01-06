package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class SetFetchSizeExample {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();

            //
            // Set the fetch size to 100. 
            //
            statement.setFetchSize(100);

            //
            // Execute the given sql query
            //
            String q = "select code, name, price, qty from products";
            ResultSet rs = statement.executeQuery(q);

            while (rs.next()) {
                System.out.println("code:" + rs.getString("code") +
                        ", name:" + rs.getString("name") +
                        ", price:" + rs.getString("price") +
                        ", qty:" + rs.getString("qty"));
            }

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
