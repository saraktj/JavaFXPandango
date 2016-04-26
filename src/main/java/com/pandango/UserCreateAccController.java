package com.pandango;

import com.firebase.client.FirebaseError;
import com.firebase.client.FirebaseException;
import com.pandango.model.User;
import com.pandango.model.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sara
 */
public class UserCreateAccController implements Initializable {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button button;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    public void buttonClick(ActionEvent event) throws IOException {
        User user = new User(firstName.getText(), lastName.getText(), email.getText());
        UserManager userManager = new UserManager();
        try {
            userManager.checkForBan(email.getText());
        } catch (FirebaseException e) {
            Alert loginError = new Alert(Alert.AlertType.WARNING);
            loginError.setTitle("BANNED");
            loginError.setHeaderText(null);
            loginError.setContentText("The email inputted has been banned. Please contact an administrator for further questions.");
            loginError.showAndWait();
            return;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserCreateAccController.class.getName()).log(Level.SEVERE, null, ex);
        }
        userManager.addUser(email.getText(), password.getText(), user);
        Parent screen = FXMLLoader.load(getClass().getResource("/fxml/UserLogin.fxml"));
        Stage stage = (Stage) button.getScene().getWindow();
        Scene scene = new Scene(screen); 
        stage.setScene(scene);
        stage.show();
    }
    
}