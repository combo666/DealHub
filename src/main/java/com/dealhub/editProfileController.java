package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class editProfileController {
    @FXML
    private ImageView editProfileImage;
    @FXML
    private TextField fName;
    @FXML
    private TextField address;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField contactNumber;
    @FXML
    private TextField lName;
    @FXML
    private TextField pass;
    @FXML
    private Button saveBtn ;
    @FXML
    private TextField uID;
    @FXML
    private Label fNameLabel;


    @FXML
    String searchId, firstName, lastName, oPass = "", conPass = "", garbage4 = "";
    public editProfileController() throws IOException {

        boolean found = false;

        try {
            Scanner x = new Scanner(new File("data.csv"));
            //x.useDelimiter("[,\n]");

            while (x.hasNextLine()) {
                String line = x.nextLine();
                String[] info = line.split(",");//info is an Array of splited data of users
                if (info.length >= 2) {
                    firstName = info[1].trim();
                    //System.out.printf(firstName);
                }
                if (info.length >= 3) {
                    lastName = info[2].trim();
                }


            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }

    @FXML
    public void initialize() {
        fName.setText(firstName);
        lName.setText(lastName);
    }

    List<String> lines = new ArrayList<>();

    @FXML
    public void editedData() {
        saveBtn.setOnAction(actionEvent -> {
            if (!fName.getText().equals(firstName)) {
                fName.setText(fName.getText());
                System.out.printf(fName.getText());

            };
        });

        }
    }



