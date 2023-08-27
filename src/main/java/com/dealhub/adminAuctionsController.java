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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class adminAuctionsController implements Initializable {
    @FXML
    private Button adminPostBtn;

    @FXML
    private Button bidManagementBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button endAuctionBtn;

    @FXML
    private TextField endAuctionTextField;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button roomManagementBtn;

    @FXML
    private Button userManagementBtn;

    @FXML
    private VBox vBox;



    @FXML
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

    @FXML
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

    @FXML
    public void setDashboardBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminDashboard.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ignored) {
        }

    }

    @FXML
    public void setEndAuctionBtn(ActionEvent event) throws IOException {
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
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM `uploadproducts` WHERE 1";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String searchID = resultSet.getString("id");
                if (searchID.equals(endAuctionTextField.getText())) {
                    System.out.println("Found");
                    String sql = "DELETE FROM `uploadproducts` WHERE id=?";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, endAuctionTextField.getText());
                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted.");
                    preparedStatement.close();

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminAuctions.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();

                    } catch (Exception ignored) {
                    }
                }
            }
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

    @FXML
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

    @FXML
    public void setUserManagementBtn(ActionEvent event) throws IOException {
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
            assert  connection != null ;
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM `uploadproducts` WHERE 1";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("product_name");
                String remTime = resultSet.getString("end_time");
                String state = resultSet.getString("auction_status");
                String bid = resultSet.getString("current_bid");

                System.out.println("found");

                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setMaxSize(1026, 25);

                Label nameLebel = new Label(name);
                nameLebel.setMaxSize(268, 25);
                nameLebel.setLayoutX(0);
                nameLebel.setFont(Font.font("Arial",15));

                Label idLebel = new Label(id);
                idLebel.setMaxSize(229, 25);
                idLebel.setLayoutX(268);
                idLebel.setFont(Font.font("Arial",15));

                Label bidsLebel = new Label(bid);
                bidsLebel.setMaxSize(193, 25);
                bidsLebel.setLayoutX(497);
                bidsLebel.setFont(Font.font("Arial",15));

                Label stateLebel = new Label(state);
                stateLebel.setMaxSize(178, 25);
                stateLebel.setLayoutX(690);
                stateLebel.setFont(Font.font("Arial",15));

                Label timeLebel = new Label(remTime);
                stateLebel.setMaxSize(158, 25);
                timeLebel.setLayoutX(868);
                timeLebel.setFont(Font.font("Arial",15));

                System.out.println("found");

                /*hBox.getChildren().add(nameLebel);
                hBox.getChildren().add(idLebel);
                hBox.getChildren().add(bidsLebel);
                hBox.getChildren().add(stateLebel);
                hBox.getChildren().add(timeLebel);*/

                anchorPane.getChildren().addAll(nameLebel,idLebel,bidsLebel,stateLebel,timeLebel);
                vBox.getChildren().add(anchorPane);

                System.out.println("found");


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



















