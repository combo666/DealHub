package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


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

}

public class creatAccountController {
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

        User us = new User(fName , lName , id , pass , cPass);

        System.out.printf(" "+us.fName);


//        try {
//            FileOutputStream fos = new FileOutputStream("data.txt");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(us);
//            fos.close();
//            oos.close();
//        }catch(Exception ignored){}


    }
}
