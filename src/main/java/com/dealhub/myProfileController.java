package com.dealhub;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class myProfileController implements Initializable{


    @FXML
    private TextField searchBt;
    @FXML
    private Button editProfile;
    @FXML
    private Button uploadProduct;
    @FXML
    private Button myBids;

    @FXML
    public void goToEditProfile() throws Exception{
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("editProfile.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) editProfile.getScene().getWindow();
        primaryStage.setScene(secondScene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){}

}
