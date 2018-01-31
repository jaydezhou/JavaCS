package net.jayde.study.kodejava.example.sql;

import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.*;

public class ClobReadDemo {
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String username = "kodejava";
    private static String password = "welcome";

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT name, description, data FROM documents ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                System.out.println("Name        = " + name);
                String description = resultSet.getString(2);
                System.out.println("Description = " + description);

                File data = new File("C:\\The Appfuce Primer.txt");

                //
                // Get the character stream of our CLOB data
                //
                Reader reader = resultSet.getCharacterStream(3);
                FileWriter writer = new FileWriter(data);
                char[] buffer = new char[1];
                while (reader.read(buffer) > 0) {
                    writer.write(buffer);
                }
                writer.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
}
