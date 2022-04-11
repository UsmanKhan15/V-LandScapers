package com.example.vlandscaper.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vlandscaper.adapters.HomeRecViewAdapter;
import com.example.vlandscaper.R;
import com.example.vlandscaper.databinding.FragmentHomeBinding;
import com.example.vlandscaper.utilClasses.Home;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<Home> list;
    DatabaseReference databaseReference;
    HomeRecViewAdapter adapter;

    //Constructor
    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.homeRecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        /*
        Getting Firebase Database Reference
         */
        databaseReference = FirebaseDatabase.getInstance().getReference("data");
        list = new ArrayList<>();

        /*
        Initializing adapter and passing list
         */
        adapter = new HomeRecViewAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Home user = dataSnapshot.getValue(Home.class);
                    list.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}