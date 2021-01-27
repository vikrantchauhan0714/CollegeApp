package com.example.collegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class GalleryFullImage extends AppCompatActivity {
private PhotoView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_full_image);

        photoView=findViewById(R.id.galleryimageView);

        String image=getIntent().getStringExtra("image");

        Glide.with(this).load(image).into(photoView);
    }
}
