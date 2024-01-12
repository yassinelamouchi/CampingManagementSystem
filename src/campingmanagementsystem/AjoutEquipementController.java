/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package campingmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutEquipementController implements Initializable {
      @FXML
    private Button ajoutEquipementBtn;

    @FXML
    private Button cancelBtn;
    @FXML
    private TextField id;

    @FXML
    private TextField prix;

    @FXML
    private TextField quantity;

    @FXML
    private ChoiceBox<String> type;

    /**
     * Initializes the controller class.
     */
    
    /**
     * Initializes the controller class.
     */
        //    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    
    
    private AlertMessage alert = new AlertMessage();
    public void registerClear() {
        prix.clear();
        quantity.clear();
        type.setValue(null);
        
        
        
    }
    public void addEquipement() {

        if (prix.getText().isEmpty()
                || quantity.getText().isEmpty()
                || (type.getValue() == null)) {
            // LETS CREATE OUR ALERT MESSAGE
            alert.errorMessage("Please fill all blank fields");
        } else {

            // WE WILL CHECK IF THE USERNAME THAT USER ENTERED IS ALREADY EXIST TO OUR DATABASE 
            String checkUsername = "SELECT * FROM equipement WHERE equipement_id = '"
                    + id.getText() + "'";

            connect = Database.connectDB();

            try {

                

                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(id.getText() + " is already exist!");
                
                } else {
                    // TO INSERT THE DATA TO OUR DATABASE
                    String insertData = "INSERT INTO equipement (type, quantity,prix) VALUES(?,?,?)";

                    
                    ;

                    prepare = connect.prepareStatement(insertData);
                    
                    prepare.setString(1, type.getValue());
                    prepare.setString(2, quantity.getText());
                    prepare.setString(3, prix.getText());
                    
                    
                    

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully!");
                    registerClear();

                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
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
    public void equiList() {

        List<String> listU = new ArrayList<>();

        for (String data : Equipements.equipement) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableList(listU);
        type.setItems(listData);
    }
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        equiList();
    }    
    
}
