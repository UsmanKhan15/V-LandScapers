package com.example.vlandscaper.ui.buy;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vlandscaper.R;
import com.example.vlandscaper.adapters.BuyRecViewAdapter;
import com.example.vlandscaper.databinding.FragmentDashboardBinding;
import com.example.vlandscaper.utilClasses.Buy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    /*
    Declaring elements
     */
    private RecyclerView recyclerView;
    private FragmentDashboardBinding binding;
    private ArrayList<Buy> list;
    private EditText edtQuantity;
    DatabaseReference databaseReference;
    BuyRecViewAdapter adapter;
    SearchView searchView;
    private Button btnOk;
    int q;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.dashboardRecView);
        searchView = root.findViewById(R.id.search);
        edtQuantity = root.findViewById(R.id.edtQuantityy);
        btnOk = root.findViewById(R.id.btnQuantity);

        //edtQuantity = root.findViewById(R.id.edtQuantityy);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference("saleAndBuyData");
        list = new ArrayList<>();

        if(databaseReference != null)
        {
            adapter = new BuyRecViewAdapter(getContext(),list);
            recyclerView.setAdapter(adapter);
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
        }
        /*
        Searching data from recyclerView
         */
        if(searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
        return root;
    }

    public int popUp() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        View mView = getLayoutInflater().inflate(R.layout.quantity_dialog_activity, null);

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quan = edtQuantity.getText().toString();
                q = Integer.parseInt(quan);
            }
        });
        alert.show();
        return q;
    }

    /*
    Defining search function
     */
    private void search(String s) {
        ArrayList<Buy>  myList = new ArrayList<>();
        for(Buy object: list)
        {
            if(object.getTitlePlantAccessories().toLowerCase().contains(s.toLowerCase())){
                myList.add(object);
            }
        }
        adapter.notifyDataSetChanged();
        adapter = new BuyRecViewAdapter(getContext(),myList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}