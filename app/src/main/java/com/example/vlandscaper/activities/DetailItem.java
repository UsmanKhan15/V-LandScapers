package com.example.vlandscaper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vlandscaper.R;
import com.example.vlandscaper.utilClasses.Buy;
import com.example.vlandscaper.utilClasses.CartData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailItem extends AppCompatActivity {

    private TextView txtTitle, txtPrice, txtDesD;
    private ImageView imgD;
    private Button btnTry;
    private Buy buy = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof Buy){
            buy = (Buy) object;
        }

        txtTitle = findViewById(R.id.txtNameD);
        txtPrice = findViewById(R.id.txtPriceD);
        txtDesD = findViewById(R.id.txtDesD);
        imgD = findViewById(R.id.imgD);
        btnTry = findViewById(R.id.btnTry);

        if(buy != null)
        {
            Glide.with(getApplicationContext()).load(buy.getImgURL()).into(imgD);
            txtTitle.setText(buy.getTitlePlantAccessories());
            String modelURL = buy.getTitlePlantAccessories().toString();
            txtPrice.setText(buy.getPrice() + "");
            txtDesD.setText(buy.getDescription());
            btnTry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(buy.getCategory().equals("Plant"))
                    {
                        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference("modelURL").child(modelURL);

                        String url;
                        firebaseDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String url = snapshot.getValue(String.class);
                                if(snapshot.exists())
                                {
                                    Intent intent = new Intent(DetailItem.this, VirtualObjectActivity.class);
                                    intent.putExtra("modelName", modelURL);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(DetailItem.this, "3D model not available", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(DetailItem.this, "3D Models are only available for Plants!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}