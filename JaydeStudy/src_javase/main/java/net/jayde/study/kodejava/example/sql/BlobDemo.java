package net.jayde.study.kodejava.example.sql;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BlobDemo {
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String username = "kodejava";
    private static String password = "welcome";

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        FileInputStream fis = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);

            String sql = "INSERT INTO pictures (name, description, image) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "java.gif");
            stmt.setString(2, "Java Official Logo");

            File image = new File("D:\\Projects\\kodejava\\web\\images\\java.gif");
            fis = new FileInputStream(image);
            stmt.setBinaryStream(3, fis, (int) image.length());
            stmt.execute();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
}
