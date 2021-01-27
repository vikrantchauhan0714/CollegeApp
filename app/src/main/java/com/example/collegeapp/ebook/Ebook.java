package com.example.collegeapp.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Ebook extends AppCompatActivity {
    private RecyclerView ebookRecycler;
    private DatabaseReference reference;
    private ShimmerFrameLayout shimmerFrameLayout;
    private List<EbookData> list;
    private EbookAdapter adapter;
    LinearLayout shimmerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ebooks");

        shimmerFrameLayout=findViewById(R.id.shimmer_view_container);
        shimmerLayout=findViewById(R.id.shimmer_layout);

        ebookRecycler = findViewById(R.id.ebookRecycle);


        reference = FirebaseDatabase.getInstance().getReference().child("pdf");

        getData();
    }

    @Override
    protected void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        shimmerFrameLayout.startShimmer();
        super.onPostResume();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    EbookData data=snapshot.getValue(EbookData.class);
                    list.add(data);


                }
                adapter=new EbookAdapter(Ebook.this,list);
                ebookRecycler.setLayoutManager(new LinearLayoutManager(Ebook.this));
                shimmerFrameLayout.setVisibility(View.GONE);
                ebookRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                shimmerFrameLayout.stopShimmer();

                Toast.makeText(Ebook.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}

