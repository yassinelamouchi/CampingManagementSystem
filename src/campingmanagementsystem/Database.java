/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campingmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * 
 */
public class Database {

    public static Connection connectDB() {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection connect
                    = DriverManager.getConnection("jdbc:mysql://localhost/camping", "root", ""); // root IS OUR DEFAULT USERNAME AND EMPTY OR NULL OR BLANK TO OUR PASSWORD
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
