package com.dealhub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class homeController implements  Initializable{

    @FXML
    private GridPane postGrid;
    private final List<itemPost> posts;

    public homeController(GridPane postGrid, List<itemPost> posts) {
        this.postGrid = postGrid;
        this.posts = posts;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int col = 0;
        int row = 1;
        for (itemPost post : posts) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("item.fxml"));

            try {
                VBox postVBox = fxmlLoader.load();

                itemController itemController = fxmlLoader.getController();
                itemController.setData(post);

                if (col == 3) {
                    col = 0;
                    row++;
                }

                postGrid.add(postVBox, col++, row);
                GridPane.setMargin(postVBox, new Insets(10));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<itemPost> data(){
        List<itemPost> ls = new ArrayList<>();

        itemPost post = new itemPost();
        post.setItemName("Halim");
        post.setItemImage("cartImagei_Phone.png");

        return ls;

    }
}
