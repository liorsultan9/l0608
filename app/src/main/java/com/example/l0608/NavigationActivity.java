package com.example.l0608;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView feedRecyclerView;
    private FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize RecyclerView
        feedRecyclerView = findViewById(R.id.feedRecyclerView);
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Placeholder data
        List<FeedItem> feedItems = new ArrayList<>();
        feedAdapter = new FeedAdapter(feedItems);
        feedRecyclerView.setAdapter(feedAdapter);

        // Fetch posts from Firestore
        db.collection("posts").get().addOnSuccessListener(queryDocumentSnapshots -> {
            // Convert documents to FeedItem objects and add them to feedItems
            // ...

            // Update the RecyclerView
            feedAdapter.setFeedItems(feedItems);
            feedAdapter.notifyDataSetChanged();
        });

        // Add Post button
        findViewById(R.id.addPostFab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to post creation activity
            }
        });
    }
}
