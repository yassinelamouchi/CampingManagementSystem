/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package campingmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author yassine lamouchi
 */
public class AffichageController implements Initializable {

    @FXML
    private AnchorPane affichage;


    @FXML
    private Button btn_acceuil;

    @FXML
    private Button btn_ajout_camp;

    @FXML
    private Button btn_ajout_camper;

    @FXML
    private Button btn_ajout_equipe;

    @FXML
    private Button btn_ajout_equipement;

    @FXML
    private Button btn_camp;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_report;

    @FXML
    private TableColumn<camp, ?> camp_camp_espace;

    @FXML
    private TableColumn<camp, ?> camp_col_capacity;

    @FXML
    private TableColumn<camp, ?> camp_col_id;

    @FXML
    private TableColumn<camp, ?> camp_col_prix;

    @FXML
    private TableColumn<camp, ?> camp_col_type;

    @FXML
    private TableColumn<Camper, ?> camper_col_datein;

    @FXML
    private TableColumn<Camper, String> camper_col_dateout;

    @FXML
    private TableColumn<Camper, ?> camper_col_id;

    @FXML
    private TableColumn<Camper, ?> camper_col_nom;

    @FXML
    private TableColumn<Camper, ?> camper_col_prenom;

    @FXML
    private TableColumn<Camper, ?> camper_col_tele;

    @FXML
    private TableColumn<Employe, ?> employe_col_id;

    @FXML
    private TableColumn<Employe, ?> employe_col_nom;

    @FXML
    private TableColumn<Employe, ?> employe_col_prenom;

    @FXML
    private TableColumn<Employe, ?> employe_col_role;

    @FXML
    private TableColumn<Employe, ?> employe_col_tel;

    @FXML
    private TableColumn<Inventory, ?> equipement_col_prix;

    @FXML
    private TableColumn<Inventory, ?> equipement_col_quantite;

    @FXML
    private TableColumn<Inventory, ?> equipement_col_type;

    @FXML
    private VBox sidebar;

    @FXML
    private TableView<camp> tab_camp;

    @FXML
    private TableView<Camper> tab_camper;

    @FXML
    private TableView<Employe> tab_equipe;

    @FXML
    private TableView<Inventory> tab_equipement;

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
    private TableView<reservation> tab_reservation;
    @FXML
    private TableColumn<facture, ?> facture_col_id;

    @FXML
    private TableColumn<facture, ?> facture_col_nom;

    @FXML
    private TableColumn<facture, ?> facture_col_prenom;

    @FXML
    private TableColumn<facture, ?> facture_col_prix;

    @FXML
    private TableView<facture> tab_facture;
    @FXML
    private Button btn_delete_camp;

    @FXML
    private Button btn_delete_camper;

    @FXML
    private Button btn_delete_equipe;

    @FXML
    private Button btn_delete_equipement;
    @FXML
    private Button btn_delete_reservation;

    //    DATABASE TOOLSs
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private final AlertMessage alert = new AlertMessage();
    @FXML
    private TextField numIdSearch;

    public ObservableList<camp> campGetData() {

        ObservableList<camp> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM camp ";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            camp appData;

            while (result.next()) {

                appData = new camp(result.getInt("camp_id"),
                        result.getString("type"), result.getInt("capacity"),
                        result.getInt("espace"), result.getDouble("prix")
                );

                // STORE ALL DATA
                listData.add(appData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<reservation> reservationGetData() {

        ObservableList<reservation> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM reservation ";

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

    public ObservableList<facture> factureGetData() {

        ObservableList<facture> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM facture ";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            facture appData;

            while (result.next()) {

                appData = new facture(result.getInt("camper_id"),
                        result.getString("nom"), result.getString("prenom"),
                        result.getDouble("prix")
                );

                // STORE ALL DATA
                listData.add(appData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<Inventory> equipementGetData() {

        ObservableList<Inventory> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM equipement ";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Inventory appData;

            while (result.next()) {

                appData = new Inventory(result.getInt("equipement_id"),
                        result.getString("type"), result.getInt("quantity"),
                        result.getDouble("prix")
                );

                // STORE ALL DATA
                listData.add(appData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<Camper> camperGetData() {

        ObservableList<Camper> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM camper ";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Camper appData;

            while (result.next()) {

                appData = new Camper(result.getInt("camper_id"),
                        result.getString("nom"), result.getString("prenom"),
                        result.getInt("telephone"), result.getDate("datein"),
                        result.getDate("dateout")
                );

                // STORE ALL DATA
                listData.add(appData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<Employe> employeGetData() {

        ObservableList<Employe> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM employe ";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Employe appData;

            while (result.next()) {

                appData = new Employe(result.getInt("employe_id"),
                        result.getString("nom"), result.getString("prenom"),
                        result.getInt("telephone"), result.getString("role")
                );

                // STORE ALL DATA
                listData.add(appData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }



    public void campShowData() {
        campListData = campGetData();

        camp_col_id.setCellValueFactory(new PropertyValueFactory<>("num_Id"));
        camp_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        camp_col_capacity.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        camp_camp_espace.setCellValueFactory(new PropertyValueFactory<>("espace"));
        camp_col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        tab_camp.setItems(campListData);
        // Add event handler for row selection
        tab_camp.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Handle row selection, if needed
                System.out.println("Selected Row: " + newSelection);
            }
        });

        // Add event handler for delete button
        btn_delete_camp.setOnAction(event -> deleteSelectedCamp());
    }

    public void employeShowData() {
        employeListData = employeGetData();

        employe_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        employe_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        employe_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        employe_col_tel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        employe_col_role.setCellValueFactory(new PropertyValueFactory<>("role"));

        tab_equipe.setItems(employeListData);

    }

    public void equipementShowData() {
        equipementListData = equipementGetData();

        equipement_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        equipement_col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        equipement_col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        tab_equipement.setItems(equipementListData);

    }

    public void camperShowData() {
        camperListData = camperGetData();

        camper_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        camper_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        camper_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        camper_col_tele.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        camper_col_datein.setCellValueFactory(new PropertyValueFactory<>("dateCheckIn"));
        camper_col_dateout.setCellValueFactory(new PropertyValueFactory<>("dateCheckOut"));

        tab_camper.setItems(camperListData);

    }

    public void factureShowData() {
        factureListData = factureGetData();

        facture_col_id.setCellValueFactory(new PropertyValueFactory<>("camper_id"));
        facture_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        facture_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        facture_col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        tab_facture.setItems(factureListData);

    }

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

        // Add event handler for delete button
        btn_delete_reservation.setOnAction(event -> deleteSelectedReservation());

    }

    @FXML
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

    @FXML
    public void homepage() {
        try {

            // LINK MAIN FORM FOR ADMIN
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
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

    @FXML
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
            btn_ajout_camper.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
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
            btn_ajout_camp.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
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
            btn_ajout_equipe.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
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
            btn_ajout_equipement.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedCamper() {
        Camper selectedCamper = tab_camper.getSelectionModel().getSelectedItem();

        if (selectedCamper != null) {
            // Remove the selected camper from the table
            tab_camper.getItems().remove(selectedCamper);

            // Delete the selected camper from the database
            deleteCamperFromDatabase(selectedCamper);
        }
    }

    private void deleteSelectedReservation() {
        reservation selectedReservation = tab_reservation.getSelectionModel().getSelectedItem();

        if (selectedReservation != null) {
            // Remove the selected camper from the table
            tab_reservation.getItems().remove(selectedReservation);

            // Delete the selected camper from the database
            deleteReservationFromDatabase(selectedReservation);
        }
    }

    private void deleteSelectedCamp() {
        camp selectedCamp = tab_camp.getSelectionModel().getSelectedItem();

        if (selectedCamp != null) {
            // Remove the selected camp from the table
            tab_camp.getItems().remove(selectedCamp);

            // Delete the selected camp from the database
            deleteCampFromDatabase(selectedCamp);
        }
    }

    private void deleteSelectedEmploye() {
        Employe selectedEmploye = tab_equipe.getSelectionModel().getSelectedItem();

        if (selectedEmploye != null) {
            // Remove the selected employe from the table
            tab_equipe.getItems().remove(selectedEmploye);

            // Delete the selected employe from the database
            deleteEmployeFromDatabase(selectedEmploye);
        }
    }

    private void deleteSelectedEquipement() {
        Inventory selectedEquipement = tab_equipement.getSelectionModel().getSelectedItem();

        if (selectedEquipement != null) {
            // Remove the selected equipement from the table
            tab_equipement.getItems().remove(selectedEquipement);

            // Delete the selected equipement from the database
            deleteEquipementFromDatabase(selectedEquipement);
        }
    }

    private void deleteCamperFromDatabase(Camper camperToDelete) {
        try {
            connect = Database.connectDB();

            // Replace "your_camper_table_primary_key" with the actual primary key column name
            String sql = "DELETE FROM camper WHERE camper_id = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, camperToDelete.getId());
            prepare.executeUpdate();

            // Close resources
            prepare.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void deleteCampFromDatabase(camp campToDelete) {
        // Implement the logic to delete the camp from the database
        // Use campToDelete.getNum_Id() to get the ID of the camp you want to delete
        try {
            connect = Database.connectDB();

            String sql = "DELETE FROM camp WHERE camp_id = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, campToDelete.getNum_Id());
            prepare.executeUpdate();

            // Close resources
            prepare.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void deleteEmployeFromDatabase(Employe employeToDelete) {
        try {
            connect = Database.connectDB();

            // Replace "your_employe_table_primary_key" with the actual primary key column name
            String sql = "DELETE FROM employe WHERE employe_id = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, employeToDelete.getId());
            prepare.executeUpdate();

            // Close resources
            prepare.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void deleteEquipementFromDatabase(Inventory equipementToDelete) {
        try {
            connect = Database.connectDB();

            // Replace "your_equipement_table_primary_key" with the actual primary key column name
            String sql = "DELETE FROM equipement WHERE equipement_id = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, equipementToDelete.getId());
            prepare.executeUpdate();

            // Close resources
            prepare.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void deleteReservationFromDatabase(reservation reservationtToDelete) {
        try {
            connect = Database.connectDB();

            // Replace "your_equipement_table_primary_key" with the actual primary key column name
            String sql = "DELETE FROM reservation WHERE camper_id = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, reservationtToDelete.getCamper_id());
            prepare.executeUpdate();

            // Close resources
            prepare.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campShowData();
        equipementShowData();
        camperShowData();
        employeShowData();
        reservationShowData();
        factureShowData();
        // Add event handlers for delete buttons
        btn_delete_camper.setOnAction(event -> deleteSelectedCamper());
        btn_delete_equipe.setOnAction(event -> deleteSelectedEmploye());
        btn_delete_equipement.setOnAction(event -> deleteSelectedEquipement());
        btn_delete_reservation.setOnAction(event -> deleteSelectedReservation());
    }
    public ObservableList<camp> campListData;
    public ObservableList<Inventory> equipementListData;
    public ObservableList<facture> factureListData;
    public ObservableList<Camper> camperListData;
    public ObservableList<Employe> employeListData;
    public ObservableList<reservation> reservationListData;
    @FXML
private void searchcamp(ActionEvent event) {
    String searchNumIdText = numIdSearch.getText();

    // Check if the search text is empty
    if (searchNumIdText.isEmpty()) {
        // If empty, set the table with the complete list
        // Assuming you have a method or an ObservableList called 'getAllCamps' 
        // that retrieves all camp objects
        tab_camp.setItems(campListData);
    } else {
        // If not empty, filter the list based on num_Id
        ObservableList<camp> filteredList = campListData.stream()
            .filter(c -> Integer.toString(c.getNum_Id()).contains(searchNumIdText))
            .collect(Collectors.toCollection(FXCollections::observableArrayList));

        // Set the table with the filtered list
        tab_camp.setItems(filteredList);
    }
}




}
