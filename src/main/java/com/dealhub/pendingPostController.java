package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class pendingPostController implements Initializable {
    @FXML
    private Button dashBoardBtn;

    @FXML
    private Button auctionManagementBtn;

    @FXML
    private Button usersManagementBtn;

    @FXML
    private Button roomManagementBtn;

    @FXML
    private Button adminPostBtn;

    @FXML
    private Button bidManagementBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    VBox vBox = new VBox();

    @FXML
    VBox vBox1 = new VBox();


    public void setDashBoardBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminDashBoard.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }

    }

    public void setAuctionManagementBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminAuction.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }

    }

    public void setUsersManagementBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminUserManagement.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }

    }

    public void setRoomManagementBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminRoomManagement.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }

    }

    public void setAdminPostBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminPost.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }

    }

    public void setBidManagementBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminBidManagement.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }

    }

    public void setLogoutBtn(ActionEvent event) throws IOException {
        try {
            _AUserLoginCheck.setuLId(null);
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connection connection = null;
        String jdbcUrl = "jdbc:mysql://localhost:3306/dealhub";
        String username = "root";
        String password = "";

        try {
            assert connection != null;
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database!");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        try {
            assert connection != null;
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM `auctionroom` WHERE 1";
            String sqlQuery1 = "SELECT * FROM `userdata` WHERE 1";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            ResultSet resultSet1 = statement.executeQuery(sqlQuery1);

            //userdata
            while (resultSet1.next()) {
                String userName = resultSet1.getString("first_name");
                System.out.println(userName);
                String userId = resultSet1.getString("id");
                System.out.println(userId);

                boolean matchFound = false;

                while (resultSet.next()){
                    System.out.println("here");

                    String uploaderId= resultSet.getString("uploader_id");
                    if(userId.equals(uploaderId)) {
                        String auctionId = resultSet.getString("id");
                        System.out.println(auctionId);
                        String auctionName = resultSet.getString("roomname");

                        AnchorPane anchorPane1 = new AnchorPane();
                        anchorPane1.setMaxSize(1288, 25);

                        Label userNameLebel = new Label(userName);
                        userNameLebel.setMaxSize(299, 25);
                        userNameLebel.setLayoutX(0);
                        userNameLebel.setFont(Font.font("Arial", 15));

                        Label userIdLebel = new Label(userId);
                        userIdLebel.setMaxSize(210, 25);
                        userIdLebel.setLayoutX(330);
                        userIdLebel.setFont(Font.font("Arial", 15));

                        Label auctionNameLebel = new Label(auctionName);
                        auctionNameLebel.setMaxSize(262, 25);
                        auctionNameLebel.setLayoutX(618);
                        auctionNameLebel.setFont(Font.font("Arial", 15));

                        Label auctionIdLebel = new Label(auctionId);
                        auctionIdLebel.setMaxSize(130, 25);
                        auctionIdLebel.setLayoutX(1013);
                        auctionIdLebel.setFont(Font.font("Arial", 15));

                        Button approveBtn = new Button("Approve");
                        approveBtn.setPrefSize(110, 25);
                        approveBtn.setLayoutX(1169);
                        approveBtn.setAlignment(Pos.BASELINE_CENTER);
                        approveBtn.setFont(Font.font("Calibri", 15));
                        approveBtn.setTextFill(Paint.valueOf("#ffffff"));
                        approveBtn.setBackground(Background.fill(Paint.valueOf("#00994C")));


                        anchorPane1.getChildren().addAll(userNameLebel, userIdLebel, auctionNameLebel, auctionIdLebel);
                        vBox1.getChildren().add(anchorPane1);

                        System.out.println("found");
                        matchFound = true;
                        break;
                    }
                }
                if (!matchFound) {
                    resultSet.beforeFirst();
                }


            }
        } catch (SQLException e) {
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