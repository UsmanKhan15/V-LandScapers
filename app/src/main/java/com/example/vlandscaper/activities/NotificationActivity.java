package com.example.vlandscaper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.vlandscaper.R;
import com.example.vlandscaper.adapters.CartRecViewAdapter;
import com.example.vlandscaper.adapters.HomeRecViewAdapter;
import com.example.vlandscaper.adapters.NotiRecViewAdapter;
import com.example.vlandscaper.utilClasses.Home;
import com.example.vlandscaper.utilClasses.Notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    //Declaration
    private RecyclerView recyclerView;
    private NotiRecViewAdapter notiRecViewAdapter;
    ArrayList<Notification> notificationArrayList;
    DatabaseReference databaseReference1;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        getSupportActionBar().setTitle("Notifications");

        recyclerView = findViewById(R.id.notRecView);
        notificationArrayList = new ArrayList<>();

        databaseReference1 = FirebaseDatabase.getInstance().getReference("notification");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Notification notification = dataSnapshot.getValue(Notification.class);
                    notificationArrayList.add(notification);
                }
                notiRecViewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        notiRecViewAdapter = new NotiRecViewAdapter(this, notificationArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notiRecViewAdapter);
    }

}