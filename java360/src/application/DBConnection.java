package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public Connection con;

    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "admin");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
