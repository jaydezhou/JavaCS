package net.jayde.study.kodejava.example.sql;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

public class BlobReadDemo {
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String username = "kodejava";
    private static String password = "welcome";

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT name, description, image FROM pictures ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                System.out.println("Name        = " + name);
                String description = resultSet.getString(2);
                System.out.println("Description = " + description);

                File image = new File("D:\\java.gif");
                FileOutputStream fos = new FileOutputStream(image);

                byte[] buffer = new byte[256];

                //
                // Get the binary stream of our BLOB data
                //
                InputStream is = resultSet.getBinaryStream(3);
                while (is.read(buffer) > 0) {
                    fos.write(buffer);
                }

                fos.close();
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
