package com.dealhub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class homeController implements  Initializable{

    @FXML
    private VBox postContainer;
    @FXML
    private GridPane roomContainer;
    @FXML
    private ImageView homeImage;
    Image photo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("homePageWithoutLogin.png")));

    Connection connection = null;
    String jdbcUrl = "jdbc:mysql://localhost:3306/dealhub";
    String username = "root";
    String password = "";
    List<itemPost> post;


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
                post.setItemImage(colImage);
                ls.add(post);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return ls;
    }
}
