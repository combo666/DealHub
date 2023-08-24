package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;


public class homeController implements  Initializable{

    @FXML
    private VBox postContainer;
    @FXML
    private GridPane roomContainer;
    @FXML
    private ImageView homeImage;
    @FXML
    private Button editProfileBtn;
    @FXML
    private Button homeLoginBtn;
    @FXML
    private Label logedinUserLabel;
    Image photo1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("homePageWithoutLogin.png")));
    Image photo2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("homePageWithoutLogin.png")));
    Connection connection = null;
    String jdbcUrl = "jdbc:mysql://localhost:3306/dealhub";
    String username = "root";
    String password = "";
    List<itemPost> post;

    @FXML
    public void setHomeLoginBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }

    }


    @FXML
    public void setEditProfileBtn(ActionEvent event) throws IOException {
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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database!");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        post = new ArrayList<>(getPosts());
        int col = 0;
        int row = 1;


        String uLoginCheckId = _AUserLoginCheck.getuLId();

        if (uLoginCheckId != null) {
            try {
                String sql = "SELECT * FROM `userdata` WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, uLoginCheckId);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    homeImage.setImage(photo2);
                    String logedinUName = resultSet.getString("first_name");
                    logedinUserLabel.setText(logedinUName);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            homeImage.setImage(photo1);
        }



        try{
            for (itemPost posts : post) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                VBox vBox = null;
                vBox = fxmlLoader.load();


                itemController itemController = fxmlLoader.getController();
                itemController.setData(posts);

                if(col == 4){
                    col=0;
                    ++row;
                }
                roomContainer.add(vBox,col++,row);
                GridPane.setMargin(vBox,new Insets(10));


            }
        }catch(Exception e){
            System.out.println(e);
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

    public List<itemPost> getPosts() {
        List<itemPost> ls = new ArrayList<>();

        itemPost post;

        String roomId,roomName = null,roomImage;

        try{
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM auctionroom";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {

                String colId = resultSet.getString("id");
                String colName = resultSet.getString("roomname");
                String colImage = resultSet.getString("roomimage");

                System.out.println(resultSet.getInt(1));

                post = new itemPost();
                post.setItemName(colName);
                post.setItemImage("_"+colImage);
                ls.add(post);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return ls;
    }
}
