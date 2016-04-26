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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sara
 */
public class AdminPageController implements Initializable {
    @FXML
    private TextField txtBox;
    @FXML
    private Button unlock;
    @FXML
    private Button ban;
    @FXML
    private Button promote;
    @FXML
    private Button logout;
    @FXML
    private ListView listView;
    @FXML
    private Button changeInfo;
    private List<User> users = new ArrayList<>();
    private User user;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserManager userManager = new UserManager();
        users = userManager.getUsers();
        System.out.println(users.size());
        List<String> userEmails = new ArrayList<>();
        for (User user1 : users) {
            userEmails.add(user1.getEmail());
        }
        System.out.println(userEmails.size());
        listView.setItems(FXCollections.observableList(userEmails));
    }    
    
     
    @FXML
    public void mouseClicked(MouseEvent event) {
        user = users.get(listView.getSelectionModel().getSelectedIndex());
    }
    
    @FXML
    public void btnClick(ActionEvent event) {
        if (txtBox.getText() != null) {
            for (User user1 : users) {
                if (user1.getEmail().equals(txtBox.getText())) {
                    user = user1;
                }
            }
        } else if (user == null) {
            Alert loginError = new Alert(Alert.AlertType.WARNING);
            loginError.setTitle("No user selected");
            loginError.setHeaderText(null);
            loginError.setContentText("In order to complete this action, a user's email address must be entered or selected.");
            loginError.showAndWait();
        }
        UserManager userManager = new UserManager();
        if (event.getSource() == ban) {
            userManager.banUser(user.getEmail(), user);
        } else if (event.getSource() == unlock) {
            userManager.unlockUser(user.getEmail());
        } else if (event.getSource() == promote) {
            userManager.promoteUser(user.getEmail());
        } else if (event.getSource() == logout) {
            userManager.logoutUser();
            Parent screen;
            try {
                screen = FXMLLoader.load(getClass().getResource("/fxml/UserLogin.fxml"));
                Stage stage = (Stage) logout.getScene().getWindow();
                Scene scene = new Scene(screen); 
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SearchResultsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        txtBox.clear();
        listView.getSelectionModel().clearSelection();
    }
    
    public void changeInfo(ActionEvent event) {
        Parent screen;
            try {
                screen = FXMLLoader.load(getClass().getResource("/fxml/ChangeInfo.fxml"));
                Stage stage = (Stage) changeInfo.getScene().getWindow();
                Scene scene = new Scene(screen); 
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SearchResultsController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
