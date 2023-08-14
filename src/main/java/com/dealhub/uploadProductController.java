package com.dealhub;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;

public class uploadProductController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button cancelBt;
    @FXML
    private Button submitBt;
    @FXML
    private Button uploadImageBt;
    @FXML
    private Button homeBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private Button roomBtn;
    @FXML
    private Button recentBtn;
    @FXML
    private TextField catagory;
    @FXML
    private TextField endHour;
    @FXML
    private TextField actualCost;
    @FXML
    private TextField productDelivery;
    @FXML
    private TextField companyName;
    @FXML
    private TextField productName;

    public uploadProductController() {
    }

    @FXML
    void initialize() {
    }
    @FXML
    public void CancelButtonPress() throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) cancelBt.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }
    @FXML
    public void homeButtonPress() throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) homeBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }
    @FXML
    public void profileButtonPress() throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) profileBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }
    /*@FXML
    public void roomButtonPress() throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) roomBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }
    @FXML
    public void recentButtonPress() throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) recentBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }*/
}

