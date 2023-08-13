package com.dealhub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class editProfileApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("editProfile.fxml"));

        editProfileController ctrl = fxmlLoader.getController();
        /*Image iconImage = new Image("icon.png");
        stage.getIcons().add(iconImage);*/
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        stage.setTitle("DealHub");
        stage.setScene(scene);
        stage.show();

    }
}
