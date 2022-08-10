package com.example.vlandscaper.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vlandscaper.R;

public class DetailDesignActivity extends AppCompatActivity {

    private TextView textView1, textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_design);

        textView = findViewById(R.id.textView5);
        textView1 = findViewById(R.id.textView7);
        imageView = findViewById(R.id.imageView);
    }
}