/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandango;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sara
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException { 
        Parent screen = FXMLLoader.load(getClass().getResource("/fxml/UserLogin.fxml"));
        Group root = new Group();
        root.getChildren().addAll(screen);
        Scene scene =  new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pandango");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   
}
