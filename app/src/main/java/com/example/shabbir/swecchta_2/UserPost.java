package com.example.shabbir.swecchta_2;

/**
 * Created by shabbir on 3/28/2018.
 */

public class UserPost {

    public String currentLocationAddress;
    public String currentLocationCoordinates;
    public String description;
    public String postDate;
    public String postImageURL;
    public String postId;
    public String author;
    public String userID;
    public String dumpSize;
    public String isProcessed;


    public UserPost(String currentLocationAddress,
                    String currentLocationCoordinates,
                    String description,
                    String postDate,
                    String postImageURL,
                    String postId,
                    String author,
                    String userID,String dumpSize,
                    String isProcessed) {
        this.currentLocationAddress = currentLocationAddress;
        this.currentLocationCoordinates = currentLocationCoordinates;
        this.description = description;
        this.postDate = postDate;
        this.postImageURL = postImageURL;
        this.postId = postId;
        this.author = author;
        this.userID = userID;
        this.dumpSize = dumpSize;
        this.isProcessed = isProcessed;
    }

    public String getCurrentLocationAddress() {
        return currentLocationAddress;
    }

    public void setCurrentLocationAddress(String currentLocationAddress) {
        this.currentLocationAddress = currentLocationAddress;
    }

    public String getCurrentLocationCoordinates() {
        return currentLocationCoordinates;
    }

    public void setCurrentLocationCoordinates(String currentLocationCoordinates) {
        this.currentLocationCoordinates = currentLocationCoordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostImageURL() {
        return postImageURL;
    }

    public void setPostImageURL(String postImageURL) {
        this.postImageURL = postImageURL;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
