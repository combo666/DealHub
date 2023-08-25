package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class roomPageController implements Initializable {
    @FXML
    private Button homeBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button recentBtn;

    @FXML
    private Button roomsBtn;
    List<itemPost> post;
    Connection connection = null;
    @FXML
    private GridPane itemContainer;

    @FXML
    TilePane tilePane = new TilePane();

    public static String deliveredRoomName;


    @FXML
    public void setHomeBtn(ActionEvent event)throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}
    }
    @FXML
    public void setProfileBtn(ActionEvent event)throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("myProfile.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
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
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM auctionroom";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println("here");

            while (resultSet.next()) {

                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setMaxSize(250,333);


                String roomImg = resultSet.getString("roomimage");
                String roomName= resultSet.getString("roomname");

                Label nameLabel = new Label(roomName);
                nameLabel.setPrefWidth(250);
                nameLabel.setPrefHeight(25);
                nameLabel.setAlignment(Pos.BASELINE_CENTER);
                nameLabel.setLayoutX(0);
                nameLabel.setLayoutY(294);
                nameLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
                nameLabel.setUnderline(true);
                //nameLabel.setTextAlignment(TextAlignment.CENTER);

                Button insideRoomBtn = new Button();
                insideRoomBtn.setPrefSize(250,333);
                insideRoomBtn.setLayoutX(0);
                insideRoomBtn.setOpacity(0);
                insideRoomBtn.setOnAction(e->{
                    deliveredRoomName=roomName;
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("userItem.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();

                    } catch (Exception ignored) {
                    }
                });

                if (roomImg != null) {
                    String absoluteImagePath = "_" + roomImg;
                    InputStream imageStream = getClass().getResourceAsStream(absoluteImagePath);
                    if (imageStream != null) {
                        Image image = new Image(imageStream);

                        ImageView roomImage = new ImageView(image);
                        roomImage.setFitWidth(250);
                        roomImage.setFitHeight(281);
                        roomImage.setLayoutX(0);

                        anchorPane.getChildren().addAll(roomImage,nameLabel,insideRoomBtn);

                        tilePane.getChildren().add(anchorPane);
                        System.out.println("found");

                    } else {
                        System.out.println("Image not found: " + absoluteImagePath);
                    }
                } else {
                    System.out.println("No image specified.");
                }

            }
        }catch (Exception e){}


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
