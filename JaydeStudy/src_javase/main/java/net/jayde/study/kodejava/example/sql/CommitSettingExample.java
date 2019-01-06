package net.jayde.study.kodejava.example.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class CommitSettingExample {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            //
            // DO: Get a connection to database, we need to obtain the 
            // database connection prior to executing any JDBC commands/
            //			

            //
            // Disable the auto-commit operation. By default every statement
            // executed against database in JDBC is in auto-commit mode. To 
            // disable auto-commit set it to false
            //
            connection.setAutoCommit(false);

            //
            // DO: Execute some other database operation here
            //

            //
            // Finally we must call the commit method explicitly to finish
            // all database manipulation operation
            //
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
