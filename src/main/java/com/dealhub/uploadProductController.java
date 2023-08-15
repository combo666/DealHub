package com.dealhub;

        import java.io.File;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.URL;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.nio.file.StandardCopyOption;
        import java.sql.*;
        import java.util.Objects;
        import java.util.ResourceBundle;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.stage.FileChooser;
        import javafx.stage.Stage;


public class uploadProductController {
    @FXML
    private TextField productCost;

    @FXML
    private Button cancelBt;

    @FXML
    private TextField category;

    @FXML
    private TextField companyName;

    @FXML
    private TextField endHour;

    @FXML
    private Button homeBtn;


    @FXML
    private TextField productDetails;

    @FXML
    private TextField productName;

    @FXML
    private Button profileBtn;

    @FXML
    private Button recentBtn;

    @FXML
    private Button roomBtn;

    @FXML
    private Button submitBt;
    @FXML
    private Button chooseImageBtn;
    @FXML
    private TextField fileDirectory;
    final FileChooser fc = new FileChooser();
    String fileNameWithoutSpaces = null;

    @FXML
    void CancelButtonPress(ActionEvent event) throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) cancelBt.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }

    @FXML
    void homeButtonPress(ActionEvent event) throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) homeBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }

    @FXML
    public void setChooseImageBtn(ActionEvent event){
        fc.setTitle("Choose a file");
        File file = fc.showOpenDialog(null);
        if (file != null) {
            String srcFilePath = file.getAbsolutePath();
            Path srcPath = Paths.get(srcFilePath);

            String fileNameWithSpace = srcPath.getFileName().toString();
            fileNameWithoutSpaces = fileNameWithSpace.replaceAll("\\s", "_");

            // Get the current working directory
            String currentDirectory = System.getProperty("user.dir");
            Path destinationPath = Paths.get(currentDirectory+"\\src\\main\\resources\\com\\dealhub", "_"+fileNameWithoutSpaces);

            try {
                // Copy the file
                Files.copy(srcPath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File copied to: " + destinationPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileDirectory.setText(srcFilePath);
        } else {
            System.out.println("Invalid");
        }


    }


    @FXML
    void profileButtonPress(ActionEvent event) throws IOException {
        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Parent secondSceneRoot = secondLoader.load();
        Scene secondScene = new Scene(secondSceneRoot);

        Stage primaryStage = (Stage) profileBtn.getScene().getWindow();
        primaryStage.setScene(secondScene);

    }
    @FXML
    public void setSubmitBt(ActionEvent event){
            Connection connection = null;
            String jdbcUrl = "jdbc:mysql://localhost:3306/dealhub";
            String username = "root";
            String password = "";

            String pName = productName.getText();
            String pCategory = category.getText();
            String pCName = companyName.getText();
            String pCost = productCost.getText();
            String eTime = endHour.getText();
            String pDetails = productDetails.getText();

            try {
                connection = DriverManager.getConnection(jdbcUrl, username, password);
                System.out.println("Connected to the database!");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                String sqlQuery = "INSERT INTO `uploadproducts`(`uploader_id`, `product_name`, `category`, `product_cost`, `company_name`, `end_time`, `product_details`, `product_image`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                assert connection != null;
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                preparedStatement.setString(1, "011203030");
                preparedStatement.setString(2, pName);
                preparedStatement.setString(3, pCategory);
                preparedStatement.setString(4, pCost);
                preparedStatement.setString(5, pCName);
                preparedStatement.setString(6, eTime);
                preparedStatement.setString(7, pDetails);
                preparedStatement.setString(8, fileNameWithoutSpaces);

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted.");
            } catch (SQLException e) {
                e.printStackTrace();
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
}