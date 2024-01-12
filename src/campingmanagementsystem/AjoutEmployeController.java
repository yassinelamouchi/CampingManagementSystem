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
public class AjoutEmployeController implements Initializable {
    @FXML
    private Button addEmployeBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField id;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private ChoiceBox<String> role;

    @FXML
    private TextField telephone;
    
    

    /**
     * Initializes the controller class.
     */
        //    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    
    
    private AlertMessage alert = new AlertMessage();
    public void registerClear() {
        nom.clear();
        prenom.clear();
        telephone.clear();
        
        
        
    }
    public void addEmploye() {

        if (nom.getText().isEmpty()
                || prenom.getText().isEmpty()
                || telephone.getText().isEmpty()) {
            // LETS CREATE OUR ALERT MESSAGE
            alert.errorMessage("Please fill all blank fields");
        } else {

            // WE WILL CHECK IF THE USERNAME THAT USER ENTERED IS ALREADY EXIST TO OUR DATABASE 
            String checkUsername = "SELECT * FROM employe WHERE employe_id = '"
                    + id.getText() + "'";

            connect = Database.connectDB();

            try {

                

                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(id.getText() + " is already exist!");
                
                } else {
                    // TO INSERT THE DATA TO OUR DATABASE
                    String insertData = "INSERT INTO employe (nom, prenom,telephone,role) VALUES(?,?,?,?)";

                    
                    ;

                    prepare = connect.prepareStatement(insertData);
                    
                    prepare.setString(1, nom.getText());
                    prepare.setString(2, prenom.getText());
                    prepare.setString(3, telephone.getText());
                    prepare.setString(4, role.getValue());
                    
                    

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
    public void userList() {

        List<String> listU = new ArrayList<>();

        for (String data : Roles.role) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableList(listU);
        role.setItems(listData);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userList();
        // TODO
    }    
    
}
