package com.dealhub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class itemApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("item.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("DealHub");
        stage.setScene(scene);
        stage.show();

    }
}
