package com.example.collegeapp.ui.home;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.collegeapp.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private ImageView map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        sliderLayout=view.findViewById(R.id.slider);

        // properties of slider
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);

        setSliderViews();

      map=view.findViewById(R.id.map);
      map.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              openMap();
          }
      });

        //then we create method for set all slider imzes


        return view;


    }

    private void openMap() {
        Uri uri= Uri.parse("geo:0, 0?q=Abes Engineering College ,Ghaziabaad");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void setSliderViews() {
        for(int i=0;i<5;i++){
            DefaultSliderView sliderView=new DefaultSliderView(getContext());

            switch (i)
            {
                case 0:
                    sliderView.setImageDrawable(R.drawable.abesimg);
                    break;

                case 1:
                    sliderView.setImageDrawable(R.drawable.abes);
                    break;

                case 2:
                    sliderView.setImageDrawable(R.drawable.abes1);
                    break;

                case 3:
                    sliderView.setImageDrawable(R.drawable.abes2);
                    break;

                case 4:
                    sliderView.setImageDrawable(R.drawable.abes3);
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER);
            sliderLayout.addSliderView(sliderView);
        }
    }
}
