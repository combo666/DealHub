package com.dealhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;


public class creatAccountController {

    public creatAccountController(){}

    @FXML
    private ImageView creatAccountIV;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField uniqueIDTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField confirmPasswordTF;
    @FXML
    private Button signupBt;
    @FXML
    private Button loginBt;
    boolean sceneChange = false;

    Image photoId = new Image(Objects.requireNonNull(getClass().getResourceAsStream("creatAccountIncorrectPage.png")));
    Image photoPass = new Image(Objects.requireNonNull(getClass().getResourceAsStream("creatAccountIncorrectPassPage.png")));

    @FXML
    public void loginAcc(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginApplication.class.getResource("login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ignored){}

    }

    @FXML
    public void signup(ActionEvent event) throws Exception {
        createAcc();

        if(sceneChange){
            try {
                _AUserLoginCheck.setuLId(null);
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

    private void createAcc() throws Exception{
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

        String fName = firstNameTF.getText();
        String lName = lastNameTF.getText();
        String uid = uniqueIDTF.getText();
        String pass = passwordTF.getText();
        String cPass = confirmPasswordTF.getText();

        boolean isNumeric = true;
        boolean uidFound = false;
        User us = new User(fName , lName , uid , pass , cPass);

        try{
            Double d = Double.parseDouble(uid);
        }catch (Exception e){
            isNumeric = false;
        }

        try {
            assert connection != null;
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM `userdata` WHERE 1";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // Process each row in the result set
                String columnValue = resultSet.getString("id");
                // Process other columns...
                if(Objects.equals(uid, columnValue)){
                    uidFound = true;
                }
                System.out.println(columnValue);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        int i = uid.length();
        if(!isNumeric || i != 9 ) {
            creatAccountIV.setImage(photoId);
        }else if(uidFound){
            creatAccountIV.setImage(photoId);
        }
        else if(!pass.equals(cPass)){
            creatAccountIV.setImage(photoPass);
        }else{
            /*StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(uid).append(",").append(fName).append(",").append(lName).append(",").append(pass).append(",").append(cPass).append("\n");

            try(FileWriter writer = new FileWriter("data.csv", true);) {
                writer.write(stringBuilder.toString());
                writer.close();
            }catch(IOException ignored){}*/

            try {
                assert connection != null;
                String sql = "INSERT INTO `userdata` (`id`, `first_name`, `last_name`, `contact_number`, `profileImage`, `newPassword`, `confirmPassword`) VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, uid);
                preparedStatement.setString(2, fName);
                preparedStatement.setString(3, lName);
                preparedStatement.setString(4, "Empty");
                preparedStatement.setString(5, "Empty");
                preparedStatement.setString(6, pass);
                preparedStatement.setString(7, cPass);

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted.");

                sceneChange = true;




            } catch (SQLException e) {
                e.printStackTrace();
            }
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

class User {
    String fName ;
    String lName ;
    String id ;
    String pass ;
    String cPass ;

    User(String fName, String lNamem, String id, String pass, String cPass){
        this.fName = fName;
        this.lName = lNamem;
        this.id = id;
        this.pass = pass;
        this.cPass = cPass;
    }

    @Override
    public String toString() {
        return "User{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", id='" + id + '\'' +
                ", pass='" + pass + '\'' +
                ", cPass='" + cPass + '\'' +
                '}';
    }
}
