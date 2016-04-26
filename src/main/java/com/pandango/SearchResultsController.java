package com.pandango;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.pandango.model.Movie;
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
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Sara
 */
public class SearchResultsController implements Initializable {
    @FXML
    private Text title;
    @FXML
    private TextArea synopsis;
    @FXML
    private ImageView image;
    @FXML
    private Button submit;
    @FXML
    private Button back;
    @FXML
    private HBox ratingBox;
    private final Rating rating = new Rating();
    private Movie movie;
    private UserManager userManager = new UserManager();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            // TODO
            HomeScreenController hsc = new HomeScreenController();
            movie = hsc.getMovie();
            title.setText(movie.toString());
            synopsis.setWrapText(true);
            if (!movie.getSynopsis().equals("")){
                for (int i = 0; i < movie.getSynopsis().length(); i++) {
                    
                }
                synopsis.setText("Synopsis: " + movie.getSynopsis());
            }
            image.setImage(new Image(movie.getPosters().getThumbnail()));
            rating.setOrientation(Orientation.HORIZONTAL);
            rating.setUpdateOnHover(true);
            rating.setLayoutX(214);
            rating.setLayoutY(280);
            rating.setMaxSize(100, 20);
            ratingBox.getChildren().add(rating);
            if (userManager.getRating(movie) != -1.0) {
                rating.setRating((double) userManager.getRating(movie));
            }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SearchResultsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void btnClick(ActionEvent event) {
        if (event.getSource() == submit) {
            userManager.addMovie(movie, rating.getRating());
            Parent screen;
            try {
                screen = FXMLLoader.load(getClass().getResource("/fxml/HomeScreen.fxml"));
                Stage stage = (Stage) submit.getScene().getWindow();
                Scene scene = new Scene(screen); 
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SearchResultsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Parent screen;
            try {
                screen = FXMLLoader.load(getClass().getResource("/fxml/HomeScreen.fxml"));
                Stage stage = (Stage) submit.getScene().getWindow();
                Scene scene = new Scene(screen); 
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SearchResultsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
