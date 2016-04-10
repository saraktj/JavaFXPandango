package com.pandango;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    @FXML
    private Button fileBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void buttonClick(ActionEvent event) throws IOException {
        Firebase ref = new Firebase("https://burning-inferno-8355.firebaseio.com/");
        ref.authWithPassword(usernameField.getText(), passwordField.getText(),
            new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) { /* ... */ }
            @Override
            public void onAuthenticationError(FirebaseError error) {
                // Something went wrong :(
                switch (error.getCode()) {
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        // handle a non existing user
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        // handle an invalid password
                        break;
                    case FirebaseError.NETWORK_ERROR:
                        
                        break;
                    default:
                        // handle other errors
                        break;
                }
            }
        });
        Stage stage = new Stage();
        Parent root = null;
      boolean wrong = false;
//      if (event.getSource() == groupSubmitBtn) {
//          if (groupUsernameField.getText() != null && !groupUsernameField.getText().isEmpty() &&
//                  groupPasswordField.getText() != null && !groupPasswordField.getText().isEmpty()) {
//            groupUsername = groupUsernameField.getText();
//            groupPassword = groupPasswordField.getText();
//            if (groupLogin(groupUsername,groupPassword)) {
//                stage = (Stage) groupSubmitBtn.getScene().getWindow();
//                root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
                /*destFile = new File("C:\\Users\\Sara\\Documents\\Summer\\PharmacyApp\\src\\startscreen\\logo.jpg");
                if (destFile != null) {
                    File file = new File("C:\\Users\\Sara\\Pictures\\abacus.jpg");
                    Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    InputStream in = new FileInputStream(destFile);
                    Image logo = new Image(in);
                    if (logo != null) {
                    imageLogo.setImage(logo);
                    }*/
                }
//            } else {
//                wrong = true;
//            }
//         } else {
//            wrong = true; 
//         }
//         if (wrong) {
//            Alert loginError = new Alert(Alert.AlertType.ERROR);
//            loginError.setTitle("Login Error");
//            loginError.setHeaderText(null);
//            loginError.setContentText("Username and/or password entered is not correct. Please try again.");
//            loginError.showAndWait();
//         } else {
//          Scene scene = new Scene(root);
//          stage.setScene(scene);
//          stage.show();
//         }
//     }
 
    
    
    @FXML
    public void hyperLinkClick(ActionEvent e) throws IOException {
//      Stage stage;
//      Parent root;
//      if (e.getSource() == createAccount){
//        stage = (Stage) createAccount.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("CreateNewAccountFXML.fxml"));
//        Scene scene = new Scene(root); 
//        stage.setScene(scene);
//        stage.show();
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
    
}
