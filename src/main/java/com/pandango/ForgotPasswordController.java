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
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sara
 */
public class ForgotPasswordController implements Initializable {
    @FXML
    private TextField email;
    @FXML
    private Button send;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void btnClick(ActionEvent event) {
        UserManager userManager = new UserManager();
        userManager.forgotPassword(email.getText());
        try {
            Parent screen = FXMLLoader.load(getClass().getResource("/fxml/UserLogin.fxml"));
            Stage stage = (Stage) send.getScene().getWindow();
                    Scene scene = new Scene(screen); 
                    stage.setScene(scene);
                    stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
