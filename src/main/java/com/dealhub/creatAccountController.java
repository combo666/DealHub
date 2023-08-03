package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;




public class creatAccountController {

    public creatAccountController(){}

    @FXML
    private ImageView creatAccountIV;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField uniqueIDTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField confirmPasswordTF;
    @FXML
    private Button signupBt;
    @FXML
    private Button loginBt;

    Image photoId = new Image(Objects.requireNonNull(getClass().getResourceAsStream("creatAccountIncorrectPage.png")));
    Image photoPass = new Image(Objects.requireNonNull(getClass().getResourceAsStream("creatAccountIncorrectPassPage.png")));

    @FXML
    public void loginAcc(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}

    }

    @FXML
    public void signup(ActionEvent event) throws Exception {
        createAcc();
    }

    private void createAcc() throws Exception{
        String fName = firstNameTF.getText();
        String lName = lastNameTF.getText();
        String id = uniqueIDTF.getText();
        String pass = passwordTF.getText();
        String cPass = confirmPasswordTF.getText();

        boolean isNumeric = true;
        User us = new User(fName , lName , id , pass , cPass);

        try{
            Double d = Double.parseDouble(id);
        }catch (Exception e){
            isNumeric = false;
        }

        int i = id.length();
        System.out.println(i);
        if(!isNumeric || i != 9 ) {
            creatAccountIV.setImage(photoId);
        }else if(!pass.equals(cPass)){
            creatAccountIV.setImage(photoPass);
        }else{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(id).append(",").append(fName).append(",").append(lName).append(",").append(pass).append(",").append(cPass).append("\n");

            try(FileWriter writer = new FileWriter("data.csv", true);) {
                writer.write(stringBuilder.toString());
                writer.close();
            }catch(IOException ignored){}
        }

    }

}

class User {
    String fName ;
    String lName ;
    String id ;
    String pass ;
    String cPass ;

    User(String fName, String lNamem, String id, String pass, String cPass){
        this.fName = fName;
        this.lName = lNamem;
        this.id = id;
        this.pass = pass;
        this.cPass = cPass;
    }

    @Override
    public String toString() {
        return "User{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", id='" + id + '\'' +
                ", pass='" + pass + '\'' +
                ", cPass='" + cPass + '\'' +
                '}';
    }
}
