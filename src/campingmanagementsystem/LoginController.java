/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package campingmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {
     @FXML
    private AnchorPane login_form;

    @FXML
    private Button login_loginBtn;

    @FXML
    private PasswordField login_password;

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private TextField login_username;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private Hyperlink register_loginHere;

    @FXML
    private PasswordField register_password;

    @FXML
    private Button register_signupBtn;

    @FXML
    private TextField register_username;
    
    //    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    
    private AlertMessage alert = new AlertMessage();


  
    public void switchForm(ActionEvent event) {

        if (event.getSource() == login_registerHere) {
            // REGISTRATION FORM WILL SHOW
            login_form.setVisible(false);
            register_form.setVisible(true);
        } else if (event.getSource() == register_loginHere) {
            // LOGIN FORM WILL SHOW
            login_form.setVisible(true);
            register_form.setVisible(false);
        }

    }
     public void registerClear() {
        register_email.clear();
        register_username.clear();
        register_password.clear();
        
    }
    public void registerAccount() {

        if (register_email.getText().isEmpty()
                || register_username.getText().isEmpty()
                || register_password.getText().isEmpty()) {
            // LETS CREATE OUR ALERT MESSAGE
            alert.errorMessage("Please fill all blank fields");
        } else {

            // WE WILL CHECK IF THE USERNAME THAT USER ENTERED IS ALREADY EXIST TO OUR DATABASE 
            String checkUsername = "SELECT * FROM admin WHERE username = '"
                    + register_username.getText() + "'";

            connect = Database.connectDB();

            try {

                

                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(register_username.getText() + " is already exist!");
                } else if (register_password.getText().length() < 8) { // CHECK IF THE CHARACTERS OF THE PASSWORD IS LESS THAN TO 8
                    alert.errorMessage("Invalid Password, at least 8 characters needed");
                } else {
                    // TO INSERT THE DATA TO OUR DATABASE
                    String insertData = "INSERT INTO admin (email, username, password) VALUES(?,?,?)";

                    
                    ;

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, register_email.getText());
                    prepare.setString(2, register_username.getText());
                    prepare.setString(3, register_password.getText());
                    

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully!");
                    registerClear();

                    // TO SWITCH THE FORM INTO LOGIN FORM
                    login_form.setVisible(true);
                    register_form.setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void loginAccount() {

        if (login_username.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect Username/Password");
        } else {

            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

            connect = Database.connectDB();

            try {

               

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                   

                    // IF CORRECT USERNAME AND PASSWORD
                    alert.successMessage("Login Successfully!");
                    // LINK MAIN FORM FOR ADMIN
                    Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                    Stage stage = new Stage();

                    stage.setTitle("WeCamp");
                    stage.setScene(new Scene(root));
                    Image icon = new Image("/Images/tent3.jpg");
                    stage.getIcons().add(icon);
                    stage.show();

                    // TO HIDE YOUR ADMIN PAGE (LOGIN FORM)
                    login_loginBtn.getScene().getWindow().hide();

                
                } else {
                    // IF WRONG USERNAME OR PASSWORD
                    alert.errorMessage("Incorrect Username/Password");
                    
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
