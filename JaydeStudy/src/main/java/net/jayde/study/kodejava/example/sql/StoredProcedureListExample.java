package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class StoredProcedureListExample {
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String username = "kodejava";
    private static String password = "welcome";

    public static void main(String[] args) throws Exception {
        Connection conn = getConnection();

        try {
            DatabaseMetaData metadata = conn.getMetaData();
            ResultSet result = metadata.getProcedures(null, "KODEJAVA", "%");
            while (result.next()) {
                System.out.println(result.getString("PROCEDURE_CAT")
                        + " - " + result.getString("PROCEDURE_SCHEM")
                        + " - " + result.getString("PROCEDURE_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    private static Connection getConnection() throws Exception {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new Exception(e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return conn;
    }

    private static void closeConnection(Connection conn) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
