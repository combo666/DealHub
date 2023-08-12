package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class roomPageController {
    @FXML
    private Button homeBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button recentBtn;

    @FXML
    private Button roomsBtn;

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
}
