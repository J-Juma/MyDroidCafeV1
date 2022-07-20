package com.mad.mydroidcafev1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PastriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastries);


        TextView pastryTitle = findViewById(R.id.donut_title);
        pastryTitle.setText(getIntent().getStringExtra("dTitle"));

        TextView pastryDescription = findViewById(R.id.donut_description);
        pastryDescription.setText(getIntent().getStringExtra("dDescription"));

        ImageView pastryImage = findViewById(R.id.donut_image);
        Glide.with(this).load(getIntent().getIntExtra("dImage", 0)).into(pastryImage);
    }
}