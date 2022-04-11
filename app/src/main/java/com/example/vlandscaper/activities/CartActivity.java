package com.example.vlandscaper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.vlandscaper.R;
import com.example.vlandscaper.adapters.BuyRecViewAdapter;
import com.example.vlandscaper.adapters.CartRecViewAdapter;
import com.example.vlandscaper.utilClasses.Buy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartRecViewAdapter adapter;
    private ArrayList<Buy> list;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Changing Title of Action Bar
        getSupportActionBar().setTitle("Cart");

        recyclerView = findViewById(R.id.cartRecView);
        databaseReference = FirebaseDatabase.getInstance().getReference("cartItem");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Buy user = dataSnapshot.getValue(Buy.class);
                    list.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter = new CartRecViewAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
}