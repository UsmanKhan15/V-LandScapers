package com.example.vlandscaper.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vlandscaper.R;
import com.example.vlandscaper.utilClasses.Buy;
import com.example.vlandscaper.utilClasses.CartData;

public class DetailItem extends AppCompatActivity {

    private TextView txtTitle, txtPrice, txtDesD;
    private ImageView imgD;
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

        if(buy != null)
        {
            Glide.with(getApplicationContext()).load(buy.getImgURL()).into(imgD);
            txtTitle.setText(buy.getTitlePlantAccessories());
            txtPrice.setText(buy.getPrice() + "");
            txtDesD.setText(buy.getDescription());
        }
    }
}