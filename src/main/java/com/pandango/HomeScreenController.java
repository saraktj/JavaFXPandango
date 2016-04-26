package com.pandango;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.pandango.model.Constants;
import com.pandango.model.Movie;
import com.pandango.model.OutputRottenTomatoes;
import com.pandango.model.UserManager;
import com.sun.javafx.scene.control.behavior.TabPaneBehavior;
import com.sun.javafx.scene.control.skin.TabPaneSkin;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SkinBase;
//import javafx.scene.control.SkinBase;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
/**
 * FXML Controller class
 *
 * @author Sara
 */
public class HomeScreenController implements Initializable, Constants {
    @FXML
    private TextField searchText;
    @FXML
    private Button searchBtn;
    @FXML
    private ListView listView;
    @FXML
    private ListView listView1;
    @FXML
    private Tab newReleasesTab;
    @FXML
    private Tab searchTab;
    @FXML
    private Tab topRentals;
    @FXML
    private TabPane tabPane;
    @FXML
    private Button profile;
    @FXML
    private Button logout;
    @FXML
    private ListView listView2;
    private List<Movie> movieList;
    private static Movie movie;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    public void btnClick(ActionEvent event) throws IOException {
        UserManager userManager = new UserManager();
        if (event.getSource() == searchBtn) {
            String url = ROTTENTOMATOES_API + SEARCH_MOVIES + API_KEY;
            listView.setItems(get(url, searchText.getText()));
        } else if (event.getSource() == logout) {
            userManager.logoutUser();
            Parent screen = FXMLLoader.load(getClass().getResource("/fxml/UserLogin.fxml"));
            Stage stage = (Stage) listView.getScene().getWindow();
            Scene scene = new Scene(screen); 
            stage.setScene(scene);
            stage.show();
        } else {
            try {
                Parent screen = FXMLLoader.load(getClass().getResource("/fxml/Profile.fxml"));
                Stage stage = (Stage) listView.getScene().getWindow();
                Scene scene = new Scene(screen); 
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void mouseClickedSearch(MouseEvent event) {
        if (event.getSource() == listView) {
            movie = movieList.get(listView.getSelectionModel().getSelectedIndex());
        } else if (event.getSource() == listView1) {
            movie = movieList.get(listView1.getSelectionModel().getSelectedIndex());
        } else if (event.getSource() == listView2) {
            movie = movieList.get(listView2.getSelectionModel().getSelectedIndex());
        }
        try {
            Parent screen = FXMLLoader.load(getClass().getResource("/fxml/SearchResults.fxml"));
            Stage stage = (Stage) listView.getScene().getWindow();
            Scene scene = new Scene(screen); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void checkTab(Event event) throws IOException {
        if (event.getSource() == newReleasesTab) {
            String url = ROTTENTOMATOES_API + OPENING_MOVIES + API_KEY;
            listView1.setItems(get(url, searchText.getText()));
        } else if (event.getSource() == topRentals) {
            String url = ROTTENTOMATOES_API + TOP_RENTALS_MOVIES + API_KEY;
            listView2.setItems(get(url, searchText.getText()));
        }
    }
    
    private ObservableList<String> get(String url, String title) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        if (title != null || !title.equals("")) {
            url = url + "&q=";
            try {
                URI uri = new URI(URLEncoder.encode(title, "UTF-8"));
                url += uri.toASCIIString();
            } catch (URISyntaxException ex) {
                Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	HttpGet request = new HttpGet(url);
//        Map<String, String> params = new HashMap<>();
//        params.put()
	HttpResponse response = client.execute(request);
        Gson gson = new Gson();
        if (response.getEntity() != null) {
            String result = EntityUtils.toString(response.getEntity());
//            System.out.println(result);
            OutputRottenTomatoes out = gson.fromJson(result, OutputRottenTomatoes.class);
//            System.out.println(out.getTotal());
            movieList = out.getMovies();
            List<String> movies = new ArrayList<>();
            for (Movie movie : out.getMovies()) {
                movies.add(movie.toString());
            }
            return FXCollections.observableList(movies);
        } 
        return null;
    }
    
    public static Movie getMovie() {
        return movie;
    }

    public static void setMovie(Movie movie) {
        HomeScreenController.movie = movie;
    }
}
