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
        import java.time.LocalDateTime;
        import java.time.temporal.ChronoUnit;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Objects;
        import java.util.ResourceBundle;

        import javafx.application.Application;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.TextArea;
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
    private ComboBox<String> categoryCB;

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
    public void initialize() {
        List<String> categories = getCategoriesFromDatabase();
        categoryCB.getItems().addAll(categories);  // Make sure to use the correct variable name here
    }

    private List<String> getCategoriesFromDatabase() {
        List<String> categories = new ArrayList<>();
        String jdbcUrl = "jdbc:mysql://localhost:3306/dealhub";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String query = "SELECT roomname FROM `auctionroom`";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String categoryName = resultSet.getString("roomname");
                categories.add(categoryName);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }


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
        String pCategory = categoryCB.getValue();
        String pCName = companyName.getText();
        String pCost = productCost.getText();
        String sTime = endHour.getText();
        long eTime = Long.parseLong(sTime);
        String pDetails = productDetails.getText();

        String sDuration = endHour.getText();
        long auctionDuration = Long.parseLong(sDuration);

        LocalDateTime currentTimestamp = LocalDateTime.now();
        LocalDateTime endTimestamp = currentTimestamp.plus(eTime, ChronoUnit.MINUTES);
        Timestamp sqlEndTimestamp = Timestamp.valueOf(endTimestamp);

        if (pCategory != null) {
            try {
                connection = DriverManager.getConnection(jdbcUrl, username, password);
                System.out.println("Connected to the database!");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                String sqlQuery = "INSERT INTO `uploadproducts`(`uploader_id`, `product_name`, `category`, `product_cost`, `current_bid`, `company_name`, `end_time`, `auction_status`, `pending_status`, `product_details`, `product_image`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                assert connection != null;
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                preparedStatement.setString(1, "011203030");
                preparedStatement.setString(2, pName);
                preparedStatement.setString(3, pCategory);
                preparedStatement.setString(4, pCost);
                preparedStatement.setString(5, "0");  // Set current_bid to initial value
                preparedStatement.setString(6, pCName);
                preparedStatement.setTimestamp(7, sqlEndTimestamp);
                preparedStatement.setString(8, "yes");  // Set auction_status to 1 (assuming active)
                preparedStatement.setString(9, "yes");  // Set pending_status to 0 (assuming not pending)
                preparedStatement.setString(10, pDetails);
                preparedStatement.setString(11, fileNameWithoutSpaces);

                preparedStatement.setTimestamp(7, sqlEndTimestamp);

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted.");


                FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("home.fxml"));
                Parent secondSceneRoot = secondLoader.load();
                Scene secondScene = new Scene(secondSceneRoot);

                Stage primaryStage = (Stage) homeBtn.getScene().getWindow();
                primaryStage.setScene(secondScene);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
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
}