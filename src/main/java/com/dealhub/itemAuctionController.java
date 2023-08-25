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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class itemAuctionController implements Initializable {
    @FXML
    private Label CurrentPriceLabel;

    @FXML
    private Label MinPriceLabel;

    @FXML
    private TextField descriptionLabel;

    @FXML
    private Label endsOnLabel;

    @FXML
    private Button homeBtn;

    @FXML
    private Label itemIdLabel;

    @FXML
    private ImageView itemImage;

    @FXML
    private Button placeBidBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button recentBtn;

    @FXML
    private Button sectionBtn;

    @FXML
    private Label sellerLabel;

    @FXML
    private TextField setYourBidTF;

    @FXML
    private Label timeLeftLabel;
    @FXML
    private Label itemName;
    @FXML
    private Label setYourBidLabel;


    private String productPrice;
    private String productCurrBid;
    private int myBid,productPriceI;




    Connection connection = null;
    String jdbcUrl = "jdbc:mysql://localhost:3306/dealhub";
    String username = "root";
    String password = "";

    @FXML
    void setHomeBtn(ActionEvent event) throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) homeBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }
    @FXML
    void setProfileBtn(ActionEvent event) throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("editProfile.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) profileBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }


    @FXML
    void setPlaceBidBtn(ActionEvent event) throws IOException {

        String text = setYourBidTF.getText();
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database!");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        try {

            _AUserLoginCheck.setMyBidTrack(Integer.parseInt(text));
            productPriceI = Integer.parseInt(productPrice);

            System.out.println("myBid: " + _AUserLoginCheck.getMyBidTrack());
            System.out.println("productPriceI: " + productPriceI);


        } catch (NumberFormatException e) {
            setYourBidLabel.setText("Invalid bid input.");

            e.printStackTrace();
        }


        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("itemAuction.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) profileBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database!");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }



        try {
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM `uploadproducts` WHERE id = 6";
            ResultSet resultSet = statement.executeQuery(sqlQuery);


            if (resultSet.next()) {
                String itemId = resultSet.getString("id");
                String pName = resultSet.getString("product_name");
                String endOn = resultSet.getString("end_time");
                String seller = resultSet.getString("uploader_id");
                String sellerCompany = resultSet.getString("company_name");
                String itemImg = resultSet.getString("product_image");
                String currPrice = resultSet.getString("current_bid");
                String pPrice = resultSet.getString("product_cost");
                String pDetails = resultSet.getString("product_details");

                String userDataSql = "SELECT * FROM `userdata` WHERE id = ?";
                PreparedStatement userDataStatement = connection.prepareStatement(userDataSql);
                userDataStatement.setString(1, seller);
                ResultSet userDataResult = userDataStatement.executeQuery();

                itemIdLabel.setText(itemId);
                itemName.setText(pName);
                endsOnLabel.setText(sellerCompany);
                timeLeftLabel.setText(endOn);
                CurrentPriceLabel.setText(currPrice);
                productCurrBid = currPrice;
                MinPriceLabel.setText(pPrice);
                productPrice = pPrice;
                descriptionLabel.setText(pDetails);


                productPriceI = Integer.parseInt(productPrice);
                myBid = Integer.parseInt(currPrice);
                System.out.println(productPriceI);

                if(_AUserLoginCheck.getMyBidTrack() == 0) {
                    System.out.println(" ");
                }else if (_AUserLoginCheck.getMyBidTrack() < productPriceI || _AUserLoginCheck.getMyBidTrack() < myBid) {
                    setYourBidLabel.setText("You need to bid higher!");
                    System.out.println("you can not");

                } else {
                    String updateQuery = "UPDATE `uploadproducts` SET `current_bid` = ? WHERE id = 6";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setInt(1, _AUserLoginCheck.getMyBidTrack()); // Set the value of myBid to the first placeholder
                    int rowsUpdated = preparedStatement.executeUpdate();
                    setYourBidLabel.setText("Bid placed successfully!");

                }

                System.out.println(myBid);


                if (userDataResult.next()) {
                    String sellerfName = userDataResult.getString("first_name");
                    String sellerlName = userDataResult.getString("last_name");
                    sellerLabel.setText(sellerfName+" "+sellerlName);

                }

                if (itemImg != null) {
                    String absoluteImagePath = "_" + itemImg;
                    InputStream imageStream = getClass().getResourceAsStream(absoluteImagePath);
                    if (imageStream != null) {
                        Image image = new Image(imageStream);
                        itemImage.setImage(image);
                    } else {
                        System.out.println("Image not found: " + absoluteImagePath);
                    }
                } else {
                    System.out.println("No image specified.");
                }
            } else {
                System.out.println("No results found.");
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
}
