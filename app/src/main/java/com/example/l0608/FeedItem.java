package com.example.l0608;

import java.util.ArrayList;
import java.util.List;

public class FeedItem {
    private String postId;  // unique ID for each post
    private String userId;  // ID of the user who made the post
    private String username;
    private String postContent;
    private long timestamp; // when the post was created
    private String imageUrl; // URL if there's an image attached to the post
    private int likesCount;  // number of likes on the post
    private List<String> comments; // list of comments on the post
    private List<String> likedByUsers; // list of user IDs who liked the post

    // Default constructor for Firebase
    public FeedItem() {}

    // Overloaded constructor
    public FeedItem(String postId, String userId, String username, String postContent, long timestamp, String imageUrl) {
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.postContent = postContent;
        this.timestamp = timestamp;
        this.imageUrl = imageUrl;
        this.likesCount = 0;  // starts at 0
        this.comments = new ArrayList<>();  // starts empty
        this.likedByUsers = new ArrayList<>();  // starts empty
    }

    // Getters
    public String getPostId() {
        return postId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPostContent() {
        return postContent;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public List<String> getComments() {
        return comments;
    }

    public List<String> getLikedByUsers() {
        return likedByUsers;
    }

    // Setters
    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public void setLikedByUsers(List<String> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    // Other methods
    public void addComment(String comment) {
        this.comments.add(comment);
    }

    public void addLike(String userId) {
        if (!this.likedByUsers.contains(userId)) {
            this.likedByUsers.add(userId);
            this.likesCount += 1;
        }
    }

    public void removeLike(String userId) {
        if (this.likedByUsers.contains(userId)) {
            this.likedByUsers.remove(userId);
            this.likesCount -= 1;
        }
    }
}
