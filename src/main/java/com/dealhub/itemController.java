package com.dealhub;
import com.dealhub.itemPost;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class itemController implements Initializable {

    @FXML
    private VBox cartBox;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemName;

    private itemPost getPost(){
        itemPost post = new itemPost();
        return post;

    }

    public void setData(itemPost post){

        try{
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(post.getItemImage())));
            itemImage.setImage(image);
            itemName.setText(post.getItemName());
        }catch (Exception e){
            System.out.println(e);
        }


    }

    @FXML
    public void item(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("item.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData(getPost());
    }

    public void getChildren() {
    }
}
