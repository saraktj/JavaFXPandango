package com.pandango;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.FirebaseException;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.pandango.model.User;
import com.pandango.model.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sara
 */
public class UserLoginController implements Initializable {
    /**
     * Initializes the controller class.
     */
    @FXML
    private Button submitBtn;
    @FXML
    private Hyperlink createAccount;
    @FXML
    private TextField usernameField ;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Hyperlink userForgotInfo;
    private User currentUser;
    private boolean wait = false;
    private List<String> locked;
    private List<String> banned;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserManager userManager = new UserManager();
        locked = userManager.checkForLocked("");
        banned = userManager.checkForBan("");
    }    
    @FXML
    public void buttonClick(ActionEvent event) throws IOException {
        UserManager userManager = new UserManager();
        boolean loggedIn = userManager.loginUser(usernameField.getText(), passwordField.getText());
      try {
        Thread.sleep(2100);
      } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      currentUser = userManager.getUser();
        if (!loggedIn){
            if (currentUser.getLoginAttempts() >= 3) {
                userManager.lockUser(currentUser);
                Alert loginError = new Alert(Alert.AlertType.WARNING);
                loginError.setTitle("Incorrect Password");
                loginError.setHeaderText(null);
                loginError.setContentText("Your account has been locked. Please contact an administrator.");
                loginError.showAndWait();
            } else {
                Alert loginError = new Alert(Alert.AlertType.WARNING);
                loginError.setTitle("Incorrect Password");
                loginError.setHeaderText(null);
                loginError.setContentText("You have used " + currentUser.getLoginAttempts() + " login attempts. You have three until your account is locked.");
                loginError.showAndWait();
            }
        } else {
            if (locked.contains(usernameField.getText())) {
                Alert loginError = new Alert(Alert.AlertType.WARNING);
                loginError.setTitle("Account Locked");
                loginError.setHeaderText(null);
                loginError.setContentText("Your account has been locked. Please contact an administrator.");
                loginError.showAndWait();
            }
            if (banned.contains(usernameField.getText())) {
                Alert loginError = new Alert(Alert.AlertType.WARNING);
                loginError.setTitle("BANNED");
                loginError.setHeaderText(null);
                loginError.setContentText("The email inputted has been banned. Please contact an administrator for further questions.");
                loginError.showAndWait();
            }
            if (!locked.contains(usernameField.getText()) && !banned.contains(usernameField.getText())) {
                if (currentUser.getAdmin() == 0) {
                    Parent screen = FXMLLoader.load(getClass().getResource("/fxml/HomeScreen.fxml"));
                    Stage stage = (Stage) submitBtn.getScene().getWindow();
                    Scene scene = new Scene(screen); 
                    stage.setScene(scene);
                    stage.show();
                } else {
                    Parent screen = FXMLLoader.load(getClass().getResource("/fxml/AdminPage.fxml"));
                    Stage stage = (Stage) submitBtn.getScene().getWindow();
                    Scene scene = new Scene(screen); 
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
    }
    
    @FXML
    public void hyperLinkClick(ActionEvent e) throws IOException {
      Stage stage;
      Parent root;
      if (e.getSource() == createAccount){
        stage = (Stage) createAccount.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/UserCreateNewAccount.fxml"));
        Scene scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();
      } else {
            stage = (Stage) createAccount.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/ForgotPassword.fxml"));
            Scene scene = new Scene(root); 
            stage.setScene(scene);
            stage.show();
      }
//      } else if (e.getSource() == createGroupAccount) {
//        stage = (Stage) createGroupAccount.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("GroupCreateNewAccountFXML.fxml"));
//        Scene scene = new Scene(root); 
//        stage.setScene(scene);
//        stage.show();
//      }
    }
//    private boolean groupLogin(String username, String password) {
//               return true;
//    }
//    public String[] getGroupInfo() {
//        String[] output= {groupUsername, groupPassword};
//        return output;
//    }
//    /*@FXML
//    public void qPopUp(ActionEvent e) {
//        Tooltip alert1 = new Tooltip("Set security question in case username/password is forgotten.");
//        Tooltip alert2 = new Tooltip("WARNING! Make sure to remember this answer. If forgotten, there is no way to retrieve your password.");
//        groupSecurityQuestion.setTooltip(alert1);
//        groupSecurityAnswer.setTooltip(alert2);
//        System.out.println(alert1.isActivated());
//    } */
//    public void fileFind(ActionEvent e) throws IOException {
//        FileChooser chooser = new FileChooser();
//        File file = chooser.showOpenDialog((Stage) fileBtn.getScene().getWindow());
//        if (file != null) {
//            logoLoc.setText(file.getPath());
//            String fileName = file.getName();
//            String filePath = new String("C:\\Users\\Sara\\Documents\\Summer\\PharmacyApp\\src\\startscreen" + fileName);
//            destFile = new File(filePath);
//            Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        }
//    }
//    public void createBtnClick(ActionEvent e) {
//        
//    }
    public void loginAlert(int loginAttempts) {
        if (loginAttempts >= 3) {
                Firebase loginRef = new Firebase("https://burning-inferno-8355.firebaseio.com/locked");
                loginRef.push().setValue(currentUser);
                Alert loginError = new Alert(Alert.AlertType.WARNING);
                loginError.setTitle("Incorrect Password");
                loginError.setHeaderText(null);
                loginError.setContentText("Your account has been locked. Please contact an administrator.");
                loginError.showAndWait();
            } else {
                Alert loginError = new Alert(Alert.AlertType.WARNING);
                loginError.setTitle("Incorrect Password");
                loginError.setHeaderText(null);
                loginError.setContentText("You have used " + loginAttempts + " login attempts. You have three until your account is locked.");
                loginError.showAndWait();
            }
    }
}
