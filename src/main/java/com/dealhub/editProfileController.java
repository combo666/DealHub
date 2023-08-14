package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class editProfileController /*implements Initializable */{
    @FXML
    private ImageView editProfileImage;
    @FXML
    private TextField fName;
    @FXML
    private PasswordField newPass;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button homeBtn;
    @FXML
    private TextField contactNumber;
    @FXML
    private TextField lName;
    @FXML
    private PasswordField confPass;
    @FXML
    private Button saveBtn;
    @FXML
    private TextField uID;
    @FXML
    private Label passVerify;

    @FXML
    String searchId, firstName, lastName, oPass = "", conPass = "", garbage4 = "";
    String[] info;
    List<String> lines = new ArrayList<>();

    public editProfileController() throws IOException {

        boolean found = false;

        try {
            Scanner x = new Scanner(new File("data.csv"));
            //x.useDelimiter("[,\n]");

            while (x.hasNextLine()) {
                String line = x.nextLine();
                info = line.split(",");//info is an Array of splited data of users

                if (info.length <= 5) {
                    searchId = info[0].trim();
                    firstName = info[1].trim();
                    lastName = info[2].trim();
                    oPass = info[3].trim();
                    conPass = info[4].trim();
                }

            }
            x.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    public void initialize() {
        fName.setText(firstName);
        lName.setText(lastName);
    }

    @FXML
    public void editedData() throws IOException {
        if (confPass.getText().equals(info[4])&&(!fName.getText().equals(firstName)||!lName.getText().equals(lastName))) {
            info[1] = fName.getText();
            info[2] = lName.getText();
            lines.add(String.join(",", info));
            try (FileWriter writer = new FileWriter("data.csv")) {
                for (String line : lines) {
                    writer.write(line + System.lineSeparator());
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            firstName = info[1];
            lastName = info[2];
            passVerify.setText("");
            if(confPass.getText().equals(info[4])){
                FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
                Parent secondSceneRoot = secondLoader.load();
                Scene secondScene = new Scene(secondSceneRoot);

                Stage primaryStage = (Stage) saveBtn.getScene().getWindow();
                primaryStage.setScene(secondScene);
            }

        }
        else if(confPass.getText().isEmpty()){
            passVerify.setText("Please provide your password");
        }
        else if(!confPass.getText().equals(info[4])){
            passVerify.setText("Password is incorrect");
        }
        else if(confPass.getText().isEmpty()){
            passVerify.setText("Please provide your password");
        }

    }
    @FXML
    public void CancelButtonPress() throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) cancelBtn.getScene().getWindow();
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

    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editedData();
    }*/
}



