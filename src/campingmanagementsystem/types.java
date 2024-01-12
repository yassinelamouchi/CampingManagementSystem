/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campingmanagementsystem;


import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class types {

    public static List<String> typesList = new ArrayList<>();

    // Method to fetch types from the equipement table and populate the typesList
    public static void fetchTypesFromDatabase() {
        Connection connect = null;
        PreparedStatement prepare = null;
        ResultSet result = null;

        try {
            // Your database connection code
            connect = Database.connectDB();

            // Your SQL query to fetch types from the equipement table
            String sql = "SELECT DISTINCT type FROM equipement";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            // Populate the typesList with the retrieved types
            while (result.next()) {
                typesList.add(result.getString("type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Your other methods and variables can go here
}