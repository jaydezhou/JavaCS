package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class TransactionRollbackExample {
    private static final String url = "jdbc:mysql://localhost/sampledb";
    private static final String username = "root";
    private static final String password = "";

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);

            String query = "INSERT INTO orders (username, order_date) " +
                    "VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, "javaduke");
            stmt.setDate(2, new Date(System.currentTimeMillis()));
            stmt.execute();

            ResultSet keys = stmt.getGeneratedKeys();
            int id = 1;
            if (keys.next()) {
                id = keys.getInt(1);
            }

            //
            // This is an invalid statement that will cause exception to 
            // demonstrate a rollback.
            //
            query = "INSERT INTO order_details (order_id, product_id, " +
                    "quantity, price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement detailStmt = conn.prepareStatement(query);
            detailStmt.setInt(1, id);
            detailStmt.setString(2, "P0000001");
            detailStmt.setInt(3, 10);
            detailStmt.setDouble(4, 100);
            detailStmt.execute();

            //
            // Commit transaction to mark it as a success database operation
            //
            conn.commit();
            System.out.println("Transaction commit...");
        } catch (SQLException e) {
            //
            // Rollback any database transaction due to exception occurred
            //
            if (conn != null) {
                conn.rollback();
                System.out.println("Transaction rollback...");
            }
            e.printStackTrace();
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
}
