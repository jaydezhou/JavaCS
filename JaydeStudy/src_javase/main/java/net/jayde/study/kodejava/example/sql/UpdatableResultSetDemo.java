package net.jayde.study.kodejava.example.sql;

import java.sql.*;

public class UpdatableResultSetDemo {

    public static void main(String[] args) {
        Connection connection = null;

        try {
            //
            // Routine to get a connection object to database.
            //
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/testdb",
                    "root", "");

            //
            // Create an updatable resultset. It means that instead of using a
            // separate sql comment to update the data we can update it directly
            // in the resultset object.
            //
            // What makes it updatable is because when creating the statement we
            // ask the connection object to create statement with CONCUR_UPDATABLE.
            // The updatable doesn't need to be TYPE_SCROLL_SENSITIVE, but adding
            // this parameter to the statement enable us to go back and forth to
            // update the data.
            //
            Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT id, product_code, product_name, quantity, price FROM products";
            ResultSet uprs = statement.executeQuery(query);

            System.out.println("id\tcode\tname\tquantity\tquantity\tprice");
            while (uprs.next()) {
                System.out.println(uprs.getString("id") + "\t"
                        + uprs.getString("product_code") + "\t"
                        + uprs.getString("product_name") + "\t"
                        + uprs.getInt("quantity") + "\t"
                        + uprs.getDouble("price"));
            }

            //
            // Move to the first row and update the resultset data. After we
            // update the rowset value we call the updateRow() method to update
            // the data in the database.
            //
            uprs.first();
            uprs.updateString("product_name", "UML Distilled 3rd Edition");
            uprs.updateRow();

            //
            // Move to the next resultset row and delete the row in the resultset
            // and apply it to the database.
            //
            uprs.next();
            uprs.deleteRow();

            //
            // Insert a new row in the resultset object with the moveToInsertRow()
            // method. Supply the information to be inserted and finally call the
            // insertRow() method to insert record to the database.
            //
            uprs.moveToInsertRow();
            uprs.updateString("product_code", "P0000010");
            uprs.updateString("product_name", "Data Structures, Algorithms");
            uprs.updateInt("quantity", 10);
            uprs.updateDouble("price", 50.99);
            uprs.insertRow();

            uprs.beforeFirst();
            System.out.println("id\tcode\tname\tquantity\tquantity\tprice");
            while (uprs.next()) {
                System.out.println(uprs.getString("id") + "\t"
                        + uprs.getString("product_code") + "\t"
                        + uprs.getString("product_name") + "\t"
                        + uprs.getInt("quantity") + "\t"
                        + uprs.getDouble("price"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
