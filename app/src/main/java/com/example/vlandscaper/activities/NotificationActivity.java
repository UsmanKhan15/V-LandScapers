package com.example.vlandscaper.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.vlandscaper.R;
import com.example.vlandscaper.adapters.NotiRecViewAdapter;
import com.example.vlandscaper.utilClasses.Notification;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    //Declaration
    private RecyclerView recyclerView;
    private NotiRecViewAdapter notiRecViewAdapter;
    ArrayList<Notification> notificationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        getSupportActionBar().setTitle("Notifications");

        recyclerView = findViewById(R.id.notRecView);

        notificationArrayList = new ArrayList<>();
        notificationArrayList.add(new Notification("Okkk", "12:30"));
        notificationArrayList.add(new Notification("pkkkk", "11:20"));
//        notiRecViewAdapter.notifyDataSetChanged();
        notiRecViewAdapter = new NotiRecViewAdapter(this, notificationArrayList);

        recyclerView.setAdapter(notiRecViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}