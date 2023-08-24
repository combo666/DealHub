package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ResourceBundle;

public class adminPostController {
    @FXML
    private Button auctionManagementBtn;

    @FXML
    private Button bidManagementBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button chooseAnImageBtn;

    @FXML
    private Label chooseAnImageLabel;

    @FXML
    private Button dashBoardBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button roomManagementBtn;

    @FXML
    private Button submitBtn;

    @FXML
    private Button userManagementBtn;
    @FXML
    private TextField roomNameTF;
    @FXML
    private TextField roomDetailsTF;
    final FileChooser fc = new FileChooser();
    String fileNameWithoutSpaces = null;

    @FXML
    public void setLogoutBtn(ActionEvent event) throws IOException {
        try {
            _AUserLoginCheck.setuLId(null);
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}

    }
    @FXML
    public void setDashBoardBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminDashboard.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}

    }
    @FXML
    public void setAuctionManagementBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminAuctions.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}

    }
    @FXML
    public void setUserManagementBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminUserManagement.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}

    }
    @FXML
    public void setRoomManagementBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminRoomManagement.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}

    }
    @FXML
    public void setBidManagementBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminBidManagement.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}

    }
    @FXML
    public void setSubmitBtn(ActionEvent event) throws IOException {
        Connection connection = null;
        String jdbcUrl = "jdbc:mysql://localhost:3306/dealhub";
        String username = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database!");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }


        try {
            assert connection != null;

            String insertQuery = "INSERT INTO `auctionroom`(`roomname`, `roomimage`, `roomdetails`) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);


            String roomNameValue,roomImageValue,roomDetailsValue;

            roomNameValue = roomNameTF.getText();
            roomDetailsValue = roomDetailsTF.getText();



            preparedStatement.setString(1, roomNameValue); // Set room name value
            preparedStatement.setString(2, fileNameWithoutSpaces); // Set room image value
            preparedStatement.setString(3, roomDetailsValue); // Set room details value

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminDashboard.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            }catch (Exception ignored){}

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
    @FXML
    public void setCancelBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminDashboard.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}

    }
    @FXML
    public void setChooseAnImageBtn(ActionEvent event) throws IOException {

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
                chooseAnImageLabel.setText(srcFilePath);
            } else {
                System.out.println("Invalid");
            }




    }

}
