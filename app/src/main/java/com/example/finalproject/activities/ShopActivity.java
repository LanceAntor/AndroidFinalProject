package com.example.finalproject.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalproject.R;

public class ShopActivity extends AppCompatActivity {
    ViewFlipper imgBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop);

        imgBanner = findViewById(R.id.imgBanner);
        int slides[]={
                R.drawable.banner1, R.drawable.banner2, R.drawable.banner3
        };
        for(int slide:slides){
            bannerFliper(slide);
        }
    }
    public void bannerFliper(int image){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(image);
        imgBanner.addView(imageView);
        imgBanner.setFlipInterval(3000);
        imgBanner.setAutoStart(true);
        imgBanner.setInAnimation(this, android.R.anim.fade_in);
        imgBanner.setOutAnimation(this, android.R.anim.fade_out);
    }
}