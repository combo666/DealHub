package com.dealhub;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class editProfileController implements Initializable {
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

    public editProfileController() throws Exception {
        boolean found = false;
        Connection connection = null;
        String jdbcUrl = "jdbc:mysql://localhost:3306/dealhub";
        String username = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            assert connection != null;
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM userdata";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            loginController loginController = new loginController();

            while (resultSet.next()) {
                searchId = resultSet.getString("id");
                if (searchId.equals("011203030")) {
                    found = true;
                    System.out.println(found);
                    firstName = resultSet.getString("first_name");
                    lastName = resultSet.getString("last_name");
                    conPass = resultSet.getString("newPassword");
                    System.out.println(firstName);
                    System.out.println(lastName);
                    System.out.println(conPass);

                    Platform.runLater(() -> {
                        fName.setText(firstName);
                        lName.setText(lastName);
                        uID.setText(searchId);
                    });
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
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


    @FXML
    public void editedData() throws Exception {
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
        if (confPass.getText().equals(conPass)) {
            try {
                assert connection != null;
                String sql = "UPDATE `userdata` SET `id`=?,`first_name`=?,`last_name`=?,`contact_number`=?,`profileImage`=?,`newPassword`=?,`confirmPassword`=? WHERE `id`=? ";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, uID.getText());
                preparedStatement.setString(2, fName.getText());
                preparedStatement.setString(3, lName.getText());
                preparedStatement.setString(4, "Empty");
                preparedStatement.setString(5, "Empty");
                preparedStatement.setString(6, newPass.getText());
                preparedStatement.setString(7, newPass.getText());
                preparedStatement.setString(8, uID.getText());

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted.");
                preparedStatement.close();
                passVerify.setText("");


            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (newPass.getText().isEmpty()) {
            passVerify.setText("Please provide your Password");
        } else {
            passVerify.setText("Invalid password");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}



