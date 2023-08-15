package com.dealhub;

import java.sql.*;

public class userItemPost {
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



}
