package com.dealhub;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class myProfileController implements Initializable{

    public myProfileController(){}


    @FXML
    private TextField searchBar;
    @FXML
    private Button editProfile;
    @FXML
    private Button uploadProduct;
    @FXML
    private Button myBids;
    @FXML
    private Button homeBtn;
    @FXML
    private Button roomBtn;
    @FXML
    private Button recentBtn;
    @FXML
    private Button myBidBtn;

    @FXML
    private Button myProfile;

    @FXML
    private Button searchBt;

    @FXML
    private Button uploadProductsBtn;

    @FXML
    private Button addBalanceBtn;

    @FXML
    private TextField addBalanceTF;

    @FXML
    private Button changePhoneBtn;

    @FXML
    private TextField mobileNoTF;

    @FXML
    private Label muBalanceLabel;
    @FXML
    public void goToEditProfile() throws Exception{
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("editProfile.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) editProfile.getScene().getWindow();
        primaryStage.setScene(secondScene);
    }
    @FXML
    public void setUploadProductsBtn() throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("uploadProduct.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) homeBtn.getScene().getWindow();
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

    @FXML
    void setAddBalanceBtn(ActionEvent event) throws IOException {

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


        try {
            String uId = _AUserLoginCheck.getuLId();

            String balanceAmt = addBalanceTF.getText();
            String uBalance = null;

            String sqlQuery = "SELECT * FROM `userdata` WHERE id = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, uId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                uBalance= resultSet.getString("user_balance");
            }

            assert uBalance != null;
            int intValue = Integer.parseInt(balanceAmt)+Integer.parseInt(uBalance);
            String stringValue = Integer.toString(intValue);


            String sql = "UPDATE `userdata` SET `user_balance` = ? WHERE id = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql);

            preparedStatement2.setString(1, stringValue);
            preparedStatement2.setString(2, uId);

            int rowsAffected = preparedStatement2.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Balance updated successfully.");
            } else {
                System.out.println("Failed to update balance.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        // CONNECTION CLOSE

        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) addBalanceBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }

    @FXML
    void setChangePhoneBtn(ActionEvent event) throws IOException {
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

        try {
            String uId = _AUserLoginCheck.getuLId();
            String phoneNo = mobileNoTF.getText();

            String sqlQuery = "UPDATE `userdata` SET `contact_number`= ? WHERE id = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, phoneNo); // Set the phone number as the first parameter
            preparedStatement.setString(2, uId); // Set the user ID as the second parameter
            int rowsAffected = preparedStatement.executeUpdate(); // Execute the update query

        } catch (SQLException e) {
            e.printStackTrace();
        }



        // CONNECTION CLOSE

        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) addBalanceBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
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


        try {
            String uId = _AUserLoginCheck.getuLId();
            String uBalance = null;
            String phoneNo = null;

            String sqlQuery = "SELECT * FROM `userdata` WHERE id = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, uId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                uBalance = resultSet.getString("user_balance");
                phoneNo = resultSet.getString("contact_number");

            }
            muBalanceLabel.setText(uBalance);
            mobileNoTF.setText(phoneNo);


            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        // CONNECTION CLOSE

        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
