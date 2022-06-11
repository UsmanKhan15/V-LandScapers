package com.example.vlandscaper.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vlandscaper.R;
import com.example.vlandscaper.utilClasses.Buy;
import com.example.vlandscaper.utilClasses.Home;

public class DetailHomeActivity extends AppCompatActivity {

    private TextView txtTitleH, txtDesH;
    private ImageView imgH;
    private Home home = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_home);

        final Object object = getIntent().getSerializableExtra("details");
        if(object instanceof Home){
            home = (Home) object;
        }

        txtTitleH = findViewById(R.id.txtTitleH);
        txtDesH = findViewById(R.id.txtDesH);
        imgH = findViewById(R.id.imgH);

        if(home != null)
        {
            Glide.with(getApplicationContext()).load(home.getImgURL()).into(imgH);
            txtTitleH.setText(home.getTitle());
            txtDesH.setText(home.getDescription());

        }
    }
}