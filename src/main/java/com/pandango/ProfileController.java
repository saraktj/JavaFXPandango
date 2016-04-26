/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandango;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sara
 */
public class ProfileController implements Initializable {
    @FXML
    private Text name;
    @FXML
    private TextField major;
    @FXML
    private TextArea bio;
    @FXML
    private Button save;
    @FXML
    private Button back;
    @FXML
    private Button changeInfo;
    User user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserManager userManager = new UserManager();
        user = userManager.getUser();
        name.setText(user.getFirstName() + " " + user.getLastName());
        major.setText(user.getMajor());
        bio.setText(user.getBio());
    }    
    
    public void btnClick(ActionEvent event) {
        UserManager userManager = new UserManager();
        if (event.getSource() == save) {
            user.setBio(bio.getText());
            user.setMajor(major.getText());
            userManager.saveProfile(user);
        } else if (event.getSource() == changeInfo) {
            Parent screen;
            try {
                screen = FXMLLoader.load(getClass().getResource("/fxml/ChangeInfo.fxml"));
                Stage stage = (Stage) back.getScene().getWindow();
                Scene scene = new Scene(screen); 
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SearchResultsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Parent screen;
            try {
                screen = FXMLLoader.load(getClass().getResource("/fxml/HomeScreen.fxml"));
                Stage stage = (Stage) back.getScene().getWindow();
                Scene scene = new Scene(screen); 
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SearchResultsController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
