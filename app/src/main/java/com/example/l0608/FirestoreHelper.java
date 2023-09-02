package com.example.l0608;

import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class FirestoreHelper {
    private FirebaseFirestore db;

    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
    }

    public void addPostToFirestore(FeedItem feedItem) {
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("postId", feedItem.getPostId());
        postMap.put("userId", feedItem.getUserId());
        postMap.put("username", feedItem.getUsername());
        postMap.put("postContent", feedItem.getPostContent());
        postMap.put("timestamp", feedItem.getTimestamp());
        postMap.put("imageUrl", feedItem.getImageUrl());
        postMap.put("likesCount", feedItem.getLikesCount());
        postMap.put("comments", feedItem.getComments());
        postMap.put("likedByUsers", feedItem.getLikedByUsers());

        db.collection("feedItems").document(feedItem.getPostId()).set(postMap);
    }

    // More methods can be added to retrieve posts, update likes, add comments, etc.
}
