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
    private int myBid,productPriceI,currPriceI;




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
            //TODO: dynamic product

            String iId = "8";
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM uploadproducts where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, iId);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                String itemId = resultSet.getString("id");
                String seller = resultSet.getString("uploader_id");
                String pName = resultSet.getString("product_name");
                String pCategory = resultSet.getString("category");
                String pPrice = resultSet.getString("product_cost");
                String currPrice = resultSet.getString("current_bid");
                String sellerCompany = resultSet.getString("company_name");
                String endOn = resultSet.getString("end_time");
                String pDetails = resultSet.getString("product_details");
                String itemImg = resultSet.getString("product_image");

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

                if (itemImg != null) {
                    String absoluteImagePath = "_" + itemImg;
                    System.out.println(absoluteImagePath);
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


                productPriceI = Integer.parseInt(productPrice);
                myBid = Integer.parseInt(currPrice);

                String user = "SELECT * FROM `userdata` WHERE id = ?";
                PreparedStatement userData = connection.prepareStatement(user);
                userData.setString(1,_AUserLoginCheck.getuLId());
                ResultSet userDataRes = userData.executeQuery();

                String bidderBalance = userDataResult.getString("user_balance");
                int intValue = Integer.parseInt(bidderBalance);



                if (_AUserLoginCheck.getMyBidTrack() == 0) {
                    System.out.println(" ");
                } else if (_AUserLoginCheck.getMyBidTrack() < productPriceI || _AUserLoginCheck.getMyBidTrack() < myBid) {
                    setYourBidLabel.setText("You need to bid higher!");
                    System.out.println("you cannot");

                } else if (intValue < productPriceI || intValue < currPriceI) {
                    setYourBidLabel.setText("Balance not sufficient!");
                    System.out.println("no balance");
                }else {
                    if (userDataRes.next()) {
                        int bidderNewBalance = intValue - _AUserLoginCheck.getMyBidTrack();
                        String updateBalanceQuery = "UPDATE `userdata` SET `user_balance` = ? WHERE id = ?";
                        PreparedStatement updateBalanceStatement = connection.prepareStatement(updateBalanceQuery);
                        updateBalanceStatement.setInt(1, bidderNewBalance);
                        updateBalanceStatement.setString(2, _AUserLoginCheck.getuLId());
                        int rowsUpdatedBalance = updateBalanceStatement.executeUpdate();

                    }

                    //TODO: dynamic product id
                    String updateBidQuery = "UPDATE `uploadproducts` SET `current_bid` = ? WHERE id = ?";
                    PreparedStatement updateBidStatement = connection.prepareStatement(updateBidQuery);
                    updateBidStatement.setInt(1, _AUserLoginCheck.getMyBidTrack()); // Set the value of myBid to the first placeholder
                    updateBidStatement.setInt(2, Integer.parseInt(itemId)); // Set the item ID as the second placeholder
                    int rowsUpdatedBid = updateBidStatement.executeUpdate();
                    setYourBidLabel.setText("Bid placed successfully!");
                }

                System.out.println(myBid);


                if (userDataResult.next()) {
                    String sellerfName = userDataResult.getString("first_name");
                    String sellerlName = userDataResult.getString("last_name");
                    sellerLabel.setText(sellerfName+" "+sellerlName);

                }





            }else {
                System.out.println("No results found.");
            }
        }
        catch (SQLException e) {
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
