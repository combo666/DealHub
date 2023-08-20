package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class userItemController implements Initializable {

    @FXML
    private VBox cartBox;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemName;
    @FXML
    private Button detailsBtn;
    @FXML
    TilePane tilePane = new TilePane();
    Connection connection = null;
    String jdbcUrl = "jdbc:mysql://localhost:3306/dealhub";
    String username = "root";
    String password = "";

    public userItemController(){

    }


   /* private userItemPost getPost() {
        return new userItemPost();

    }

    public void setData(userItemPost post) {

        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(post.getItemImage())));
            itemImage.setImage(image);
            itemName.setText(post.getItemName());
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @FXML
    public void item(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("item.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }

    }

    @FXML
    public void setDetailsBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("roomPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }
    }*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database!");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        try{
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM uploadproducts";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while(resultSet.next()){

                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setMaxWidth(250);
                anchorPane.setMaxHeight(400);

                String itemImg = resultSet.getString("product_image");
                String itemName= resultSet.getString("product_name");
                String price = resultSet.getString("product_cost");

                 Label name= new Label(itemName);
                 name.setMaxSize(250,55);
                 name.setLayoutX(0);
                 name.setLayoutY(255);
                 name.setTextAlignment(TextAlignment.CENTER);
                 name.setFont(Font.font("Arial",18));

                 Label bidPrice = new Label("$"+price);
                 bidPrice.setMaxSize(75,17);
                 bidPrice.setLayoutX(75);
                 bidPrice.setLayoutY(305);
                 bidPrice.setFont(Font.font("Arial", FontWeight.BOLD,18));

                 Button placeBidBtn = new Button("Place your bid");
                 placeBidBtn.setMaxSize(201,25);
                 placeBidBtn.setLayoutX(26);
                 placeBidBtn.setLayoutY(380);
                 

                if (itemImg != null) {
                    String absoluteImagePath = "_" + itemImg;
                    InputStream imageStream = getClass().getResourceAsStream(absoluteImagePath);
                    if (imageStream != null) {
                        Image image = new Image(imageStream);

                        ImageView productImg = new ImageView(image);
                        productImg.setFitWidth(250);
                        productImg.setFitHeight(245);

                        anchorPane.getChildren().add(productImg);
                        anchorPane.getChildren().add(name);
                        anchorPane.getChildren().add(placeBidBtn);
                        anchorPane.getChildren().add(bidPrice);
                        tilePane.getChildren().add(anchorPane);
                        System.out.println("found");

                    } else {
                        System.out.println("Image not found: " + absoluteImagePath);
                    }
                } else {
                    System.out.println("No image specified.");
                }
            }


        }catch (SQLException e) {
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


        //setData(getPost());
    }

    //public void getChildren() {}

}
