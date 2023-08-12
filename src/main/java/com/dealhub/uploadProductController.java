package com.dealhub;

        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;

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
}

