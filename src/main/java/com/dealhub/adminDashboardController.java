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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class adminDashboardController implements Initializable {
    @FXML
    private Button adminPostBtn;

    @FXML
    private Label allBidsLabel;

    @FXML
    private Label allPostLabel;

    @FXML
    private Button auctionManagementBtn;

    @FXML
    private Button bidManagementBtn;
    @FXML
    private ImageView dashboardImage;

    @FXML
    private Label pendingPostLabel;

    @FXML
    private Button roomManagementBtn;

    @FXML
    private Button userManagementBtn;
    @FXML
    private Button logoutBtn;

    @FXML
    public void setAdminPostBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("adminPost.fxml"));
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
    public void setRoomManagementBtn(ActionEvent event) throws IOException{
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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            Statement statementAllBids = connection.createStatement();
            String sqlQueryAllBids = "SELECT COUNT(*) AS rowCount FROM uploadproducts WHERE auction_status = 'yes'";
            ResultSet resultSetAllBids = statementAllBids.executeQuery(sqlQueryAllBids);


            Statement statementPendingPost = connection.createStatement();
            String sqlQueryPendingPost = "SELECT COUNT(*) AS rowCount FROM uploadproducts WHERE pending_status = 'yes'";
            ResultSet resultSetPendingPost = statementPendingPost.executeQuery(sqlQueryPendingPost);

            Statement statementAllRoom = connection.createStatement();
            String sqlQueryAllRoom = "SELECT COUNT(*) AS rowCount FROM auctionroom";
            ResultSet resultSetAllRoom = statementAllRoom.executeQuery(sqlQueryAllRoom);


            if (resultSetAllBids.next()) {
                int rowCount = resultSetAllBids.getInt("rowCount");
                String rowCounti= rowCount+" ";
                allBidsLabel.setText(rowCounti);
            }

            if (resultSetPendingPost.next()) {
                int rowCount = resultSetPendingPost.getInt("rowCount");
                String rowCounti= rowCount+" ";
                pendingPostLabel.setText(rowCounti);
            }

            if (resultSetAllRoom.next()) {
                int rowCount = resultSetAllRoom.getInt("rowCount");
                String rowCounti= rowCount+" ";
                allPostLabel.setText(rowCounti);
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }



        // CONNECTION CLOSE

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
