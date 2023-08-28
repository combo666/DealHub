package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private Button homeBtn;

    @FXML
    private ImageView itemImage;
    @FXML
    private Label itemName;
    @FXML
    private Button detailsBtn;
    @FXML
    Label noSuchItm;

    @FXML
    private Button profileBtn;

    public static String deliveredItemId;
    @FXML
    TilePane tilePane = new TilePane();
    Connection connection = null;
    String jdbcUrl = "jdbc:mysql://localhost:3306/dealhub";
    String username = "root";
    String password = "";


    @FXML
    public void setHomeBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }
    }
    @FXML
    public void setProfileBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("myProfile.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }
    }


    public userItemController() {

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
            String sqlQuery = "SELECT * FROM uploadproducts";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {

                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setMaxWidth(250);
                anchorPane.setMaxHeight(400);

                String itemImg = resultSet.getString("product_image");
                String itemName = resultSet.getString("product_name");
                String price = resultSet.getString("product_cost");
                String room= resultSet.getString("category");
                String id=resultSet.getString("id");

                Label name = new Label(itemName);
                name.setPrefSize(250, 55);
                name.setLayoutX(0);
                name.setLayoutY(255);
                name.setAlignment(Pos.BASELINE_CENTER);
                //name.setTextAlignment(TextAlignment.CENTER);
                name.setFont(Font.font("Arial", 18));

                Label bidPrice = new Label("$" + price);
                bidPrice.setPrefSize(100, 17);
                bidPrice.setLayoutX(75);
                bidPrice.setLayoutY(305);
                bidPrice.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                bidPrice.setAlignment(Pos.BASELINE_CENTER);


                Button placeBidBtn = new Button("Place your bid");
                placeBidBtn.setPrefSize(201, 25);
                placeBidBtn.setLayoutX(26);
                placeBidBtn.setLayoutY(380);
                placeBidBtn.setAlignment(Pos.BASELINE_CENTER);

                placeBidBtn.setOnAction(e->{

                        if(_AUserLoginCheck.getuLId() == null){
                            try {
                            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("login.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }else {
                            try{
                            deliveredItemId = id;
                            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("itemAuction.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                });


                if (itemImg != null) {
                    String absoluteImagePath = "_" + itemImg;
                    InputStream imageStream = getClass().getResourceAsStream(absoluteImagePath);
                    if (imageStream != null) {
                        Image image = new Image(imageStream);

                        ImageView productImg = new ImageView(image);
                        productImg.setFitWidth(250);
                        productImg.setFitHeight(245);
                        System.out.println("found");

                        if(roomPageController.deliveredRoomName.equals(room)){
                            anchorPane.getChildren().add(productImg);
                            anchorPane.getChildren().add(name);
                            anchorPane.getChildren().add(placeBidBtn);
                            anchorPane.getChildren().add(bidPrice);
                            tilePane.getChildren().add(anchorPane);
                        }


                    } else {
                        System.out.println("Image not found: " + absoluteImagePath);
                    }
                } else {
                    System.out.println("No image specified.");
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
    public void homeButtonPress() throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) homeBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }


}
