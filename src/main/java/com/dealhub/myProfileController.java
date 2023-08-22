package com.dealhub;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class myProfileController implements Initializable{

    public myProfileController(){}


    @FXML
    private TextField searchBar;
    @FXML
    private Button editProfile;
    @FXML
    private Button uploadProduct;
    @FXML
    private Button myBids;
    @FXML
    private Button homeBtn;
    @FXML
    private Button roomBtn;
    @FXML
    private Button recentBtn;
    @FXML
    private Button myBidBtn;

    @FXML
    private Button myProfile;

    @FXML
    private Button searchBt;

    @FXML
    private Button uploadProductsBtn;

    @FXML
    public void goToEditProfile() throws Exception{
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("editProfile.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) editProfile.getScene().getWindow();
        primaryStage.setScene(secondScene);
    }
    @FXML
    public void setUploadProductsBtn() throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("uploadProduct.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) homeBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }
    @FXML
    public void homeButtonPress() throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) homeBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }

    /*@FXML
    public void roomButtonPress() throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) roomBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }
    @FXML
    public void recentButtonPress() throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) recentBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){}

}
