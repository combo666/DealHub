package com.dealhub;

import javafx.beans.binding.When;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.*;
import java.util.*;


public class communityServerController implements Initializable {
    @FXML
    private VBox chatVbox;

    @FXML
    private Button homeBtn;

    @FXML
    private Button myProfileBtn;

    @FXML
    private Button sendBtn;

    @FXML
    private TextField sendMassageTF;
    @FXML
    Label stateLebel;

    @FXML
    Label nameLebel;
    @FXML
    private TextField massageDisplay;

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8080;

    private Socket socket;
    private PrintWriter out;

    @FXML
    public void setSendBtn (ActionEvent event) throws IOException{

        String message = sendMassageTF.getText();
        if(sendMassageTF.equals("/exit")){
            out.println("/exit");
            closeResources();
        }else {
            out.println(message);
            sendMassageTF.clear();
        }

    }

    private void closeResources() {
        try {
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
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
            socket = new Socket(SERVER_IP, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);

            // Start a thread to listen for incoming messages
            Thread messageListener = new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage + "\n");
                        massageDisplay.setText(serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            messageListener.setDaemon(true);
            messageListener.start();
        } catch (IOException e) {
            e.printStackTrace();
        }




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
            String sqlQuery = "SELECT * FROM `community_chat` WHERE 1";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
//                String id = resultSet.getString("id");
//                String cHstory = resultSet.getString("chat_history");
//                String sby = resultSet.getString("sent_by");
//
//                System.out.println("found");
//
//                AnchorPane anchorPane = new AnchorPane();
//                anchorPane.setMaxSize(1026, 25);
//
//                stateLebel = new Label(sby);
//                stateLebel.setMaxSize(178, 25);
//                stateLebel.setLayoutX(0);
//                stateLebel.setFont(Font.font("Arial",15));
//
//                nameLebel = new Label(cHstory);
//                nameLebel.setMaxSize(268, 25);
//                nameLebel.setLayoutX(100);
//                nameLebel.setFont(Font.font("Arial",15));
//
//
//
//                System.out.println("found");
//
//
//                anchorPane.getChildren().addAll(nameLebel,stateLebel);
//                chatVbox.getChildren().add(anchorPane);
//
//                System.out.println("found");


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
