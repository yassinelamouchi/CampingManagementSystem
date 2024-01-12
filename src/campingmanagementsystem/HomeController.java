package campingmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Date;



public class HomeController implements Initializable {

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

 @FXML
    private Button bntcreatereservation;

    @FXML
    private Button btn_acceuil;

    @FXML
    private Button btn_camp;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_report;

    @FXML
    private Button btnajoutcamp;

    @FXML
    private Button btnajoutcamper;

    @FXML
    private Button btnajoutequipe;

    @FXML
    private Button btnajoutequipement;

    @FXML
    private Text nbrcamp;

    @FXML
    private Text nbrcamper;

    @FXML
    private Text nbrequipe;

    @FXML
    private Text nbrequipement;

    @FXML
    private TableColumn<reservation, ?> reservation_col_camp;

    @FXML
    private TableColumn<reservation, ?> reservation_col_date;

    @FXML
    private TableColumn<reservation, ?> reservation_col_equipement;

    @FXML
    private TableColumn<reservation, ?> reservation_col_id;

    @FXML
    private TableColumn<reservation, ?> reservation_col_nom;

    @FXML
    private TableColumn<reservation, ?> reservation_col_prenom;

    @FXML
    private TableColumn<reservation, ?> reservation_col_quantity;

    @FXML
    private VBox sidebar;

    @FXML
    private TableView<reservation> tab_reservation;

    public void addcamper() {
        try {

            // LINK MAIN FORM FOR ADMIN
            Parent root = FXMLLoader.load(getClass().getResource("AjoutCamper.fxml"));
            Stage stage = new Stage();

            stage.setTitle("WeCamp");
            stage.setScene(new Scene(root));
            Image icon = new Image("/Images/tent3.jpg");
            stage.getIcons().add(icon);
            stage.show();
            // TO HIDE YOUR ADMIN PAGE (LOGIN FORM)
            btnajoutcamper.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginPage() {
        try {

            // LINK MAIN FORM FOR ADMIN
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = new Stage();

            stage.setTitle("WeCamp");
            stage.setScene(new Scene(root));
            Image icon = new Image("/Images/tent3.jpg");
            stage.getIcons().add(icon);
            stage.show();
            // TO HIDE YOUR ADMIN PAGE (LOGIN FORM)
            btn_logout.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
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
            btn_logout.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addcamp() {
        try {

            // LINK MAIN FORM FOR ADMIN
            Parent root = FXMLLoader.load(getClass().getResource("AjoutCamp.fxml"));
            Stage stage = new Stage();

            stage.setTitle("WeCamp");
            stage.setScene(new Scene(root));
            Image icon = new Image("/Images/tent3.jpg");
            stage.getIcons().add(icon);
            stage.show();
            // TO HIDE YOUR ADMIN PAGE (LOGIN FORM)
            btnajoutcamp.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEmploye() {
        try {

            // LINK MAIN FORM FOR ADMIN
            Parent root = FXMLLoader.load(getClass().getResource("AjoutEmploye.fxml"));
            Stage stage = new Stage();

            stage.setTitle("WeCamp");
            stage.setScene(new Scene(root));
            Image icon = new Image("/Images/tent3.jpg");
            stage.getIcons().add(icon);
            stage.show();
            // TO HIDE YOUR ADMIN PAGE (LOGIN FORM)
            btnajoutequipe.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEquipement() {
        try {

            // LINK MAIN FORM FOR ADMIN
            Parent root = FXMLLoader.load(getClass().getResource("AjoutEquipement.fxml"));
            Stage stage = new Stage();

            stage.setTitle("WeCamp");
            stage.setScene(new Scene(root));
            Image icon = new Image("/Images/tent3.jpg");
            stage.getIcons().add(icon);
            stage.show();
            // TO HIDE YOUR ADMIN PAGE (LOGIN FORM)
            btnajoutequipement.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nbrcamp() {

        String sql = "SELECT COUNT(camp_id) FROM camp ";

        connect = Database.connectDB();

        int tempAP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempAP = result.getInt("COUNT(camp_id)");
            }
            nbrcamp.setText(String.valueOf(tempAP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void nbrcamper() {

        String sql = "SELECT COUNT(camper_id) FROM camper ";

        connect = Database.connectDB();

        int tempAP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempAP = result.getInt("COUNT(camper_id)");
            }
            nbrcamper.setText(String.valueOf(tempAP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void nbremploye() {

        String sql = "SELECT COUNT(employe_id) FROM employe ";

        connect = Database.connectDB();

        int tempAP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempAP = result.getInt("COUNT(employe_id)");
            }
            nbrequipe.setText(String.valueOf(tempAP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void nbrequipement() {

        String sql = "SELECT COUNT(equipement_id) FROM equipement ";

        connect = Database.connectDB();

        int tempAP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempAP = result.getInt("COUNT(equipement_id)");
            }
            nbrequipement.setText(String.valueOf(tempAP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public ObservableList<reservation> reservationGetData() {

        ObservableList<reservation> listData = FXCollections.observableArrayList();

    String sql = "SELECT * FROM reservation WHERE DATE(DATE_FORMAT(datein, '%Y-%m-%d')) = CURDATE()";



        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            reservation appData;

            while (result.next()) {

                appData = new reservation(result.getInt("camper_id"),
                        result.getString("nom"), result.getString("prenom"),
                        result.getString("camptype"), result.getString("equipementtype"),
                        result.getInt("quantity"),
                        result.getDate("datein")
                );

                // STORE ALL DATA
                listData.add(appData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    public ObservableList<reservation> reservationListData;
        public void reservationShowData() {
        reservationListData = reservationGetData();

        reservation_col_id.setCellValueFactory(new PropertyValueFactory<>("camper_id"));
        reservation_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        reservation_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        reservation_col_camp.setCellValueFactory(new PropertyValueFactory<>("camptype"));
        reservation_col_equipement.setCellValueFactory(new PropertyValueFactory<>("equipementtype"));
        reservation_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        reservation_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        tab_reservation.setItems(reservationListData);
        // Add event handler for row selection
        tab_reservation.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Handle row selection, if needed
                System.out.println("Selected Row: " + newSelection);
            }
        });



    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // You can add any initialization code here if needed
        nbrcamp();
        nbrcamper();
        nbremploye();
        nbrequipement();
                reservationShowData();
    }

    // Add any event handling methods if needed
}
