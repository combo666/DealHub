package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class dealHubController {

    @FXML
    private ImageView loginImageView;
    @FXML
    private TextField userIdTF;
    @FXML
    private TextField passTF;
    @FXML
    private Button loginBt;
    @FXML
    private Button createAccBt;

    String userId;
    String password;
    public void logIn(ActionEvent event){
        userId = userIdTF.getText();
        System.out.println(userId);
    }




}