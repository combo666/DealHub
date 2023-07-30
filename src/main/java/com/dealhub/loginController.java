package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    @FXML
    private ImageView loginImageView;
    @FXML
    private TextField uIdTF;
    @FXML
    private PasswordField passTF;
    @FXML
    private Button loginBt;
    @FXML
    private Button createAccBt;

    @FXML
    public void  creatAcc(ActionEvent event){
        
    }

    int userId;
    int password;

    private Scene scene;
    private Parent root;




    public void creatAcc(ActionEvent event) throws IOException {


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("creatAccount.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}

    }
}