package com.dealhub;

import java.sql.*;

public class roomItemPost {
    private String itemImage;
    private String profileImage;
    private String itemName;
    private String itemId;
    private String itemPrice;
    private String totalBids;
    private String time;
    private String itemDescription;

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String postImage) {
        this.itemImage = postImage;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String userName) {
        this.itemName = userName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getTotalBids() {
        return totalBids;
    }

    public void setTotalBids(String totalBids) {
        this.totalBids = totalBids;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public static void main(String[] args) {
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
            String sql = "SELECT * FROM `userdata` WHERE 1";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // Process each row in the result set
                String columnValue = resultSet.getString("id");
                // Process other columns...
                System.out.println(columnValue);
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

}
