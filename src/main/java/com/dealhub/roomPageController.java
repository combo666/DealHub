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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
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
                itemContainer.add(vBox,col++,row);
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
            System.out.println("herea");

            while (resultSet.next()) {

                String colId = resultSet.getString("id");
                String colName = resultSet.getString("roomname");
                String colImage = resultSet.getString("roomimage");

                System.out.println(resultSet.getInt(1));

                post = new itemPost();
                post.setItemName(colName);
                post.setItemImage(colImage);
                ls.add(post);
                System.out.println("Pushing..\n");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return ls;
    }
}
