package net.jayde.study.kodejava.example.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableExample {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "kodejava";
            String password = "welcome";

            String sql = "CREATE TABLE books (id NUMBER(11), title VARCHAR2(64))";
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}
