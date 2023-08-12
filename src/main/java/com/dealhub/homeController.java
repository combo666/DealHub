package com.dealhub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class homeController implements  Initializable{

    @FXML
    private VBox postContainer;
    @FXML
    private GridPane roomContainer;
    List<itemPost> post;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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
    }

    public List<itemPost> getPosts() {
        List<itemPost> ls = new ArrayList<>();

        itemPost post;

        String roomId,roomName = null,roomImage = null;

        try{
            Scanner x = new Scanner(new File("room.csv"));
            while (x.hasNext()){
                x.useDelimiter("[,\n]");
                roomId = x.next();
                roomName = x.next();
                roomImage = x.next();
                System.out.println(roomName +" "+roomImage);

                post = new itemPost();
                post.setItemName(roomId);
                post.setItemImage("cartImagei_phone.png");
                ls.add(post);
            }
        }catch (Exception e){
            System.out.println(e);
        }





//        try{
//            Scanner x = new Scanner(new File("room.csv"));
//            x.useDelimiter("[,\n]");
//            while (x.hasNext()){
//                roomId = x.next();
//                roomName = x.next();
//                roomImage = x.next();
//
//                post = new itemPost();
//                post.setItemName(roomName);
//                post.setItemImage(roomImage);
//
//                ls.add(post);
//
//            }
//
//        }catch (Exception e){
//            System.out.println(e);
//        }


        return ls;
    }
}
