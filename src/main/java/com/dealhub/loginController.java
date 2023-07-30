package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    public loginController(){}


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
    private Scene scene;
    private Parent root;

    @FXML
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
    @FXML
    public void logIn(ActionEvent event) throws IOException {
        checkLogIn();
    }
    @FXML
    private void checkLogIn() throws IOException{
        String id=uIdTF.getText();
        String pass = passTF.getText();

        if (id != null && pass != null){

        }



    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}