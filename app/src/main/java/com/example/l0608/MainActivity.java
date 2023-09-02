package com.example.l0608;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private FirestoreHelper firestoreHelper; // For Firestore integration

    // For feed UI
    private RecyclerView feedRecyclerView;
    private FeedAdapter feedAdapter;
    private List<FeedItem> feedItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String welcomeText = getString(R.string.welcome_message, currentUser.getEmail());
            Toast.makeText(this, welcomeText, Toast.LENGTH_SHORT).show();
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        firestoreHelper = new FirestoreHelper(); // Initialize Firestore helper

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            fetchLocation();
        }

        setupRecyclerView();
        setupFloatingActionButton();
    }

    private void setupRecyclerView() {
        feedRecyclerView = findViewById(R.id.feedRecyclerView);
        feedAdapter = new FeedAdapter(feedItemList);
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedRecyclerView.setAdapter(feedAdapter);
    }

    private void setupFloatingActionButton() {
        FloatingActionButton addPostFab = findViewById(R.id.addPostFab);
        addPostFab.setOnClickListener(v -> {
            // For demonstration: Add a dummy post to your feed when the FAB is clicked
            FeedItem dummyPost = new FeedItem("dummyId", "dummyUserId", "Dummy User", "This is a dummy post!", System.currentTimeMillis(), "");
            feedItemList.add(dummyPost);
            feedAdapter.notifyDataSetChanged(); // Notify the adapter about the new data
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation();
            } else {
                Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void fetchLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            storeLocationInFirebase(latitude, longitude);
                        }
                    });
        }
    }

    private void storeLocationInFirebase(double latitude, double longitude) {
        // Here, for the sake of demonstration, I'm storing the location as a new feed item
        FeedItem newPost = new FeedItem(/*... parameters ...*/);
        newPost.setPostContent("Latitude: " + latitude + ", Longitude: " + longitude); // Set the content as the location

        firestoreHelper.addPostToFirestore(newPost); // Store the post in Firestore
    }
}
