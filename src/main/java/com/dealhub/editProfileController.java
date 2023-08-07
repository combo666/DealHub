package com.dealhub;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
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
    private Button saveBtn;
    @FXML
    private TextField uID;

    String searchId = "011203030", firstName = "" , lastName = "", oPass= "", conPass = "", garbage4 = "";

    public editProfileController() throws IOException {

        boolean found = false;

        Scanner x = new Scanner(new File("data.csv"));
        x.useDelimiter("[,\n]");

        try{
            while (x.hasNext() && !found) {
                searchId = x.next();
                firstName = x.next();
                lastName = x.next();
                oPass = x.next();
                conPass = x.next();


                if (searchId.equals("011203030")) {
                    found = true;
                    System.out.println(firstName);
                    fName.setText("Enter your name");
                    fName.setFocusTraversable(false);


                }
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }


    }



}
