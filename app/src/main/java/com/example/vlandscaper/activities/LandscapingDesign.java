package com.example.vlandscaper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.vlandscaper.R;
import com.example.vlandscaper.adapters.BuyRecViewAdapter;
import com.example.vlandscaper.adapters.DesignRecViewAdapter;
import com.example.vlandscaper.utilClasses.Design;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LandscapingDesign extends AppCompatActivity {

    private RecyclerView designRecView;
    private DesignRecViewAdapter adapter;
    private ArrayList<Design> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscaping_design);

        designRecView = findViewById(R.id.designRecView);
        designRecView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("designData");
        list = new ArrayList<>();

        if(databaseReference != null)
        {
            adapter = new DesignRecViewAdapter(this,list);
            designRecView.setAdapter(adapter);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        Design design = dataSnapshot.getValue(Design.class);
                        list.add(design);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
//        adapter = new DesignRecViewAdapter(this,list);
//        designRecView.setAdapter(adapter);
    }
}