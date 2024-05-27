package com.example.finalproject.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {
    ViewFlipper imgBanner;

    private RecyclerView mRecyclerView;
    private PopularAdapter mAdapter;

    private DatabaseReference mDatabaseRef;

    private List<Popular> mPopulars;
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

        showPopularProducts();
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
    private void showPopularProducts() {
        mRecyclerView=findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        mPopulars = new ArrayList<>();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("popular");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Popular popular = postSnapshot.getValue(Popular.class);
                    mPopulars.add(popular);
                    mAdapter=new PopularAdapter(ShopActivity.this,mPopulars);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseErrorrror) {
                Toast.makeText(ShopActivity.this, databaseErrorrror.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}