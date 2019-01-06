package net.jayde.study.kodejava.example.sql;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClobInsertDemo {
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String username = "kodejava";
    private static String password = "welcome";

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        FileReader reader = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);

            String sql = "INSERT INTO documents (name, description, data) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "The AppFuse Primer.txt");
            stmt.setString(2, "The AppFuse Primer");

            File data = new File("C:\\The AppFuse Primer.txt");
            reader = new FileReader(data);
            stmt.setCharacterStream(3, reader, (int) data.length());
            stmt.execute();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
}
