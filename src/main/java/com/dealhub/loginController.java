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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

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

    private FileChooser fileChooser;
    private Scene scene;
    private Parent root;

    Image photo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("loginIncorrectIDPass.png")));

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

    String searchId = "", searchPass = "" , garbage1 = "", garbage2 = "", garbage3 = "", garbage4 = "";

    @FXML
    private void checkLogIn() throws IOException{
        String id=uIdTF.getText();
        String pass = passTF.getText();

        boolean found = false;

        Scanner x = new Scanner(new File("data.csv"));
        x.useDelimiter("[,\n]");

        while (x.hasNext() && !found){
            searchId = x.next();
            garbage1 = x.next();
            garbage2 = x.next();
            searchPass = x.next();
            garbage4 = x.next();

            if(searchId.equals(id) && searchPass.equals(pass)){
                found = true;
                System.out.println(searchId);
            }
        }


        if (id == null && pass == null || !found){
            loginImageView.setImage(photo);
        }



    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}