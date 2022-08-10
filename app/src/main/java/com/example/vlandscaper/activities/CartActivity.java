package com.example.vlandscaper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vlandscaper.R;
import com.example.vlandscaper.adapters.CartRecViewAdapter;
import com.example.vlandscaper.utilClasses.CartData;
import com.example.vlandscaper.utilClasses.OrderPlace;
import com.example.vlandscaper.utilClasses.Quantity;
import com.example.vlandscaper.utilClasses.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    //Declaration
    private RecyclerView recyclerView;
    private CartRecViewAdapter adapter;
    public ArrayList<CartData> listCart;
    static String namee;

    private DatabaseReference userNameRef;
    private DatabaseReference reference2, databaseReference;

    //Declaration to set all fields to 0;
    private static int id = 0;
    private static int total = 0;
    private static int quantity = 0;
    private static String itemsName = "";
    private int quan;
    private TextView totalAmount, totalItems;
    private Button btnPlaceOrder, btnDelete;

/*
OnCreate Method When the activity called starts from this function
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Changing Title of Action Bar
        getSupportActionBar().setTitle("Cart");

        //Initializing Elements

        recyclerView = findViewById(R.id.cartRecView);
        totalAmount = findViewById(R.id.txtTotalAmount);
        totalItems = findViewById(R.id.txtTotalItems);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        btnDelete = findViewById(R.id.btnDelete);

        /*
        Getting user name from main activity
         */
        Intent intent = getIntent();
        namee = intent.getStringExtra("userName");

        /*
        Getting value from firebase database
         */
        if(namee == null)
        {
            Toast.makeText(this, "Please Check Your Internet Connection & Retry Again", Toast.LENGTH_SHORT).show();
        }
        else {
            getDataFirebase(namee);


            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            String userId = firebaseAuth.getCurrentUser().getUid();
            userNameRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("userName");
            userNameRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        namee = snapshot.getValue().toString();
                        btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                deleteFunction(namee);
                            }
                        });
                        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                placeOrderFunction(namee);
                            }
                        });
                    } else {
                        Toast.makeText(CartActivity.this, "Something went wrong!!! Please check your connection", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            adapter = new CartRecViewAdapter(this, listCart);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }

    /*
    placeOrderFunction to place order definition
     */
    private void placeOrderFunction(String name) {
        DatabaseReference reference;
        DatabaseReference reference1;
        DatabaseReference reference3, reference4;
        if(TextUtils.isEmpty(totalAmount.getText().toString()))
        {
            Toast.makeText(CartActivity.this, "Please Add Some Items To Cart", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(totalItems.getText().toString()) || totalItems.getText().toString().equals("0")){
            Toast.makeText(CartActivity.this, "Please Add Items to Cart to Place Order", Toast.LENGTH_SHORT).show();
        }
        else
        {
            /*
                Placing Order And Deleting Record From Cart View
                 */
            reference = FirebaseDatabase.getInstance().getReference();
            String pushId = reference.child("orders").child(name).push().getKey();

            addQuantity(pushId, name);

            OrderPlace data1 = new OrderPlace(totalAmount.getText().toString(), totalItems.getText().toString(), itemsName, name, pushId);
            reference.child("orders").child(name).child(pushId).setValue(data1);
            UserData data2 = new UserData(name);
            reference3 = FirebaseDatabase.getInstance().getReference();
            reference3.child("orderU").child(name).setValue(data2);
            Toast.makeText(CartActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();

            /*
            Showing Notification
             */
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            reference4 = FirebaseDatabase.getInstance().getReference();
            reference4.child(userId).push().setValue(itemsName);

            reference1 = FirebaseDatabase.getInstance().getReference();
            reference1.child("cartItem").child(name).removeValue().
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            totalAmount.setText("0");
                            totalItems.setText("0");
                            total = 0;
                            listCart = new ArrayList<>(0);
                            //listCart.clear();
                            adapter = new CartRecViewAdapter(CartActivity.this,listCart);
                            recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
                            recyclerView.setAdapter(adapter);
                        }
                    });
        }
    }

    private void addQuantity(String pushId, String n) {
        databaseReference = FirebaseDatabase.getInstance().getReference("cartItem").child(n);
        reference2 = FirebaseDatabase.getInstance().getReference();

        //Setting Layout for Out Put
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    CartData user = dataSnapshot.getValue(CartData.class);
                    Quantity quantity = new Quantity(user.getTitlePlantA(), user.getQuantity());
                    reference2.child("quantity").child(pushId).child(user.getTitlePlantA()).setValue(quantity)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(CartActivity.this, "Something went wrong try Again", Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CartActivity.this, "Please wait for a second ", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /*
    Function to delete data from recycler view
     */
    private void deleteFunction(String name) {
        DatabaseReference reference1, reference2;
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        reference1 = FirebaseDatabase.getInstance().getReference();
        reference2 = FirebaseDatabase.getInstance().getReference();
        reference1.child("cartItem").child(name).removeValue().
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        totalAmount.setText("0");
                        totalItems.setText("0");
                        total = 0;
                        Toast.makeText(CartActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        listCart = new ArrayList<>(0);
                        adapter = new CartRecViewAdapter(CartActivity.this,listCart);
                        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
                        recyclerView.setAdapter(adapter);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CartActivity.this, "Something went wrong!!!", Toast.LENGTH_SHORT).show();
                    }
                });

        /*
        Deleting Data From Firebase
         */
        reference2.child("quantity").child(name).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    /*
    Getting value from firebase Function Definition
     */
    private void getDataFirebase(String n) {
        id = 0;
        total = 0;
        quantity = 0;
        itemsName = "";
        databaseReference = FirebaseDatabase.getInstance().getReference("cartItem").child(n);
        listCart = new ArrayList<>();

        //Setting Layout for Out Put
        databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        CartData user = dataSnapshot.getValue(CartData.class);
                        listCart.add(user);
                        try {
                            total = total + listCart.get(id).getTotalPrice();
                            quantity = quantity + listCart.get(id).getQuantity();
                            itemsName = itemsName + listCart.get(id).getTitlePlantA() + listCart.get(id).getQuantity();
                            id++;
                        }
                        catch (Throwable e){
                            Toast.makeText(CartActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    adapter.notifyDataSetChanged();
                    if(total == 0){
                        totalAmount.setText("0");
                        totalItems.setText("0");
                    }
                    else
                    {
                        totalItems.setText(quantity + "");
                        totalAmount.setText(total + "");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(CartActivity.this, "Please wait for a second ", Toast.LENGTH_SHORT).show();
                }
            });
    }


}