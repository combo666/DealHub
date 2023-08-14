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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

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

        Connection connection = null;
        String jdbcUrl = "jdbc:mysql://localhost:3306/dealhub";
        String username = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database!");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }




        String id=uIdTF.getText();
        String pass = passTF.getText();

        boolean found = false;

        try {
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM userdata";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                // Process each row in the result set
                String colid = resultSet.getString("id");
                String colpass = resultSet.getString("newPassword");
                // Process other columns...
                System.out.println(colid +" "+ colpass);
                if(id.equals(colid) && pass.equals(colpass)){
                    found = true;
                    System.out.println("found");
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("home.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();

                    }catch (Exception ignored){}
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (id == null && pass == null || !found){
            loginImageView.setImage(photo);
        }

        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}