/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandango;

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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sara
 */
public class ChangeInfoController implements Initializable {
    @FXML
    private TextField email;
    @FXML
    private TextField newEmail;
    @FXML
    private TextField email1;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField pass1;
    @FXML
    private PasswordField newPass;
    @FXML
    private Button changeEmail;
    @FXML
    private Button changePass;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void btnClick(ActionEvent event) {
        UserManager userManager = new UserManager();
        if (event.getSource() == changeEmail) {
            userManager.changeEmail(email.getText(), pass.getText(), newEmail.getText());
        } else {
           userManager.changePassword(email1.getText(), pass1.getText(), newPass.getText());
        }
        if (userManager.getUser().getAdmin() == 0) {
            try {
                Parent screen = FXMLLoader.load(getClass().getResource("/fxml/HomeScreen.fxml"));
                Stage stage = (Stage) changeEmail.getScene().getWindow();
                Scene scene = new Scene(screen); 
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ChangeInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                    Parent screen = FXMLLoader.load(getClass().getResource("/fxml/AdminPage.fxml"));
                    Stage stage = (Stage) changeEmail.getScene().getWindow();
                    Scene scene = new Scene(screen); 
                    stage.setScene(scene);
                    stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ChangeInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
    }
}
