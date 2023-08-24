package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class adminUserManagementController implements Initializable {
    @FXML
    private Button adminPostBtn;

    @FXML
    private Button auctionManagementBtn;

    @FXML
    private Button banBtn;

    @FXML
    private TextField banTF;

    @FXML
    private Button bidManagementBtn;

    @FXML
    private Button dashBoardBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button roomManagementBtn;

    @FXML
    public VBox vBox = new VBox();
   /* @FXML
    public ScrollPane scrollPane =new ScrollPane(vBox);*/

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
    public void setLogoutBtn(ActionEvent event) throws IOException{
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
    public void setBanBtn(ActionEvent event) throws IOException {
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
            String sqlQuery = "SELECT * FROM `userdata` WHERE 1";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String searchID = resultSet.getString("id");
                if (searchID.equals(banTF.getText())) {
                    System.out.println("Found");
                    String sql = "DELETE FROM `userdata` WHERE id=?";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, banTF.getText());
                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted.");
                    preparedStatement.close();

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("login.fxml"));
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
            String sqlQuery = "SELECT * FROM `userdata` WHERE 1";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {

                String name = resultSet.getString("first_name");
                String id = resultSet.getString("id");
                String balane = resultSet.getString("user_balance");

                System.out.println("found");

                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setMaxSize(1226, 25);

                Label nameLebel = new Label(name);
                nameLebel.setMaxSize(284, 25);
                nameLebel.setLayoutX(0);
                nameLebel.setFont(Font.font("Arial",15));

                Label idLebel = new Label(id);
                idLebel.setMaxSize(284, 25);
                idLebel.setLayoutX(284);
                idLebel.setFont(Font.font("Arial",15));

                Label balanceLebel = new Label(balane);
                balanceLebel.setMaxSize(284, 25);
                balanceLebel.setLayoutX(568);
                balanceLebel.setFont(Font.font("Arial",15));

                Label postLebel = new Label();
                postLebel.setMaxSize(257, 25);
                postLebel.setLayoutX(852);
                postLebel.setFont(Font.font("Arial",15));

                Label winLebel = new Label();
                winLebel.setMaxSize(117, 25);
                winLebel.setLayoutX(1109);
                winLebel.setFont(Font.font("Arial",15));

                System.out.println("found");

                anchorPane.getChildren().addAll(nameLebel,idLebel,balanceLebel,postLebel,winLebel);
                vBox.getChildren().add(anchorPane);
                //scrollPane.getChildren().add(vBox);

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

