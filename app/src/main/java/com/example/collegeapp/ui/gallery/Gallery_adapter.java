package com.example.collegeapp.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.collegeapp.GalleryFullImage;
import com.example.collegeapp.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//2nd step we extend recycler view then we implement methods......
public class Gallery_adapter  extends RecyclerView.Adapter<Gallery_adapter.GAlleryviewAdapter> {
private Context context;
private List<String> images;

    public Gallery_adapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public GAlleryviewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_image, parent, false);
        return new GAlleryviewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GAlleryviewAdapter holder, final int position) {
        //show all images.....
        Glide.with(context).load(images.get(position)).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, GalleryFullImage.class);
                intent.putExtra("image",images.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    //firstly we create this class  class
    public class GAlleryviewAdapter extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public GAlleryviewAdapter(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.gallery_image);
        }
    }
}
