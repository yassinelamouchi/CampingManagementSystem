package campingmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SideBarController implements Initializable {

   @FXML
    private Button btn_acceuil;

    @FXML
    private Button btn_camp;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_report;

    @FXML
    private VBox sidebar;
    
    
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
    public void affichagepage(){ try {

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
            }}
    public void homepage(){ try {

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
            }}

    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // You can add any initialization code here if needed
    }

    // Add any event handling methods if needed

}
