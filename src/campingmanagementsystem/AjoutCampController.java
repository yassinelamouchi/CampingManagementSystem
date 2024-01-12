/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package campingmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutCampController implements Initializable {
    
     @FXML
    private TextField capacity;

    @FXML
    private TextField espace;

    @FXML
    private TextField id;

    @FXML
    private TextField prix;

    @FXML
    private TextField type;
    @FXML
    private Button addCampBtn;

    @FXML
    private Button cancelBtn;
    
    //    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    
    
    private AlertMessage alert = new AlertMessage();
    public void registerClear() {
        id.clear();
        type.clear();
        capacity.clear();
        prix.clear();
        espace.clear();
        
    }
    public void addcamp() {

        if (type.getText().isEmpty()
                || capacity.getText().isEmpty()
                || prix.getText().isEmpty()
                || espace.getText().isEmpty()) {
            // LETS CREATE OUR ALERT MESSAGE
            alert.errorMessage("Please fill all blank fields");
        } else {

            // WE WILL CHECK IF THE USERNAME THAT USER ENTERED IS ALREADY EXIST TO OUR DATABASE 
            String checkUsername = "SELECT * FROM camp WHERE camp_id = '"
                    + id.getText() + "'";

            connect = Database.connectDB();

            try {

                

                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(id.getText() + " is already exist!");
                
                } else {
                    // TO INSERT THE DATA TO OUR DATABASE
                    String insertData = "INSERT INTO camp (type,capacity,espace,prix) VALUES(?,?,?,?)";

                    
                    ;

                    prepare = connect.prepareStatement(insertData);
                    
                    prepare.setString(1, type.getText());
                    prepare.setString(2, capacity.getText());
                    prepare.setString(3, espace.getText());
                    prepare.setString(4, prix.getText());
                   
                    

                    prepare.executeUpdate();
                


                    alert.successMessage("Registered Successfully!");
                    registerClear();

                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    

    /**
     * Initializes the controller class.
     */
    public void cancel(){ try {

                    // LINK MAIN FORM FOR ADMIN
                    Parent root = FXMLLoader.load(getClass().getResource("Affichage.fxml"));
                    Stage stage = new Stage();

                    stage.setTitle("WeCamp");
                    stage.setScene(new Scene(root));
                    Image icon = new Image("/Images/tent3.jpg");
                    stage.getIcons().add(icon);
                    stage.show();
                    // TO HIDE YOUR ADMIN PAGE (LOGIN FORM)
                    cancelBtn.getScene().getWindow().hide();

            } catch (Exception e) {
                e.printStackTrace();
            }}
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
