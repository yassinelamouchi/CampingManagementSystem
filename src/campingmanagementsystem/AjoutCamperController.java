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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static javax.management.remote.JMXConnectorFactory.connect;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutCamperController implements Initializable {

    @FXML
    private Button addCamperBtn;

    @FXML
    private Button cancelAddCamper;

    @FXML
    private DatePicker date_check_in;

    @FXML
    private DatePicker date_check_out;

    @FXML
    private TextField id;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField telephone;

    @FXML
    private Button ajoutReservationBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private ChoiceBox<String> choosecamp;

    @FXML
    private TextField quantity;

    @FXML
    private ChoiceBox<String> chooseequi;
    @FXML
    private CheckBox reservation;

    //    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    public void registerClear() {
        id.clear();
        nom.clear();
        prenom.clear();
        telephone.clear();
        choosecamp.setValue(null);
        chooseequi.setValue(null);
        quantity.clear();
        date_check_in.setValue(null);
        date_check_out.setValue(null);

    }

    public void addcamper() {

        if (nom.getText().isEmpty()
                || prenom.getText().isEmpty()
                || telephone.getText().isEmpty()) {
            // LETS CREATE OUR ALERT MESSAGE
            alert.errorMessage("Please fill all blank fields");
        } else {

            // WE WILL CHECK IF THE USERNAME THAT USER ENTERED IS ALREADY EXIST TO OUR DATABASE 
            String checkUsername = "SELECT * FROM camper WHERE camper_id = '"
                    + id.getText() + "'";

            connect = Database.connectDB();

            try {

                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(id.getText() + " is already exist!");

                } else {
                    // TO INSERT THE DATA TO OUR DATABASE
                    if (reservation.isSelected()) {
                        String insertData = "INSERT INTO camper (nom, prenom,telephone,datein,dateout) VALUES(?,?,?,?,?)";
                        ;

                        prepare = connect.prepareStatement(insertData);

                        prepare.setString(1, nom.getText());
                        prepare.setString(2, prenom.getText());
                        prepare.setString(3, telephone.getText());
                        prepare.setString(4, String.valueOf(date_check_in.getValue()));
                        prepare.setString(5, String.valueOf(date_check_out.getValue()));

                        prepare.executeUpdate();
                        alert.successMessage("Registered Successfully!");
                        registerClear();

                    } else {
                        String insertData = "INSERT INTO camper (nom, prenom,telephone,datein,dateout) VALUES(?,?,?,?,?)";
                        ;

                        prepare = connect.prepareStatement(insertData);

                        prepare.setString(1, nom.getText());
                        prepare.setString(2, prenom.getText());
                        prepare.setString(3, telephone.getText());
                        prepare.setString(4, String.valueOf(date_check_in.getValue()));
                        prepare.setString(5, String.valueOf(date_check_out.getValue()));

                        prepare.executeUpdate();

                        // TO INSERT THE SAME DATA TO THE RESERVATION TABLE
                        String insertReservationData = "INSERT INTO reservation (nom, prenom,camptype, equipementtype,quantity, datein) VALUES (?, ?, ?,?,?,?)";

                        prepare = connect.prepareStatement(insertReservationData);
                        prepare.setString(1, nom.getText());
                        // Assuming you have text fields for camper's name and surname
                        // Replace with the actual text fields or modify as needed
                        prepare.setString(2, prenom.getText());
                        prepare.setString(3, choosecamp.getValue());
                        prepare.setString(4, chooseequi.getValue());
                        prepare.setString(5, quantity.getText());

                        prepare.setString(6, String.valueOf(date_check_in.getValue()));

                        prepare.executeUpdate();
                        //TO INSERT THE SAME DATA TO THE facture TABLE
                        String insertfactureData ="INSERT INTO facture (nom, prenom, prix) " +
                            "SELECT ?, ?, ((equipement.prix * equipement.quantity) + camp.prix) " +
                            "FROM equipement, camp, reservation " +
                            "WHERE camp.type = reservation.camptype " +
                            "AND equipement.type = reservation.equipementtype";
                        prepare = connect.prepareStatement(insertfactureData);
                        prepare.setString(1, nom.getText());
                        // Assuming you have text fields for camper's name and surname
                        // Replace with the actual text fields or modify as needed
                        prepare.setString(2, prenom.getText());
                        prepare.executeUpdate();

                        alert.successMessage("Registered Successfully!");
                        registerClear();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void affichagepage() {
        try {

            // LINK MAIN FORM FOR ADMIN
            Parent root = FXMLLoader.load(getClass().getResource("Affichage.fxml"));
            Stage stage = new Stage();

            stage.setTitle("WeCamp");
            stage.setScene(new Scene(root));
            Image icon = new Image("/Images/tent3.jpg");
            stage.getIcons().add(icon);
            stage.show();
            // TO HIDE YOUR ADMIN PAGE (LOGIN FORM)
            cancelAddCamper.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void userList() {
        types.fetchTypesFromDatabase();

        List<String> listU = new ArrayList<>();

        for (String data : types.typesList) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableList(listU);
        chooseequi.setItems(listData);
    }

    public void userList1() {
        types1.fetchTypesFromDatabase();

        List<String> listU = new ArrayList<>();

        for (String data : types1.typesList) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableList(listU);
        choosecamp.setItems(listData);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userList();
        userList1();
    }

}
