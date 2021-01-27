package com.example.collegeapp.ebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapp.R;

import java.util.List;

public class EbookAdapter  extends RecyclerView.Adapter<EbookAdapter.EbookViewAdapter> {
    private Context context;
    private List<EbookData> list;

    public EbookAdapter(Context context, List<EbookData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ebook_item,parent,false);
        return new  EbookViewAdapter (view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewAdapter holder, final int position) {
        holder.ebookText.setText(list.get(position).getPdfName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(context,PdfViewerActivity.class);
               intent.putExtra("pdfUrl",list.get(position).getPdfUrl());
               context.startActivity(intent);
            }
        });

        holder.ebookDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getPdfUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EbookViewAdapter extends RecyclerView.ViewHolder {

       private TextView ebookText;
       private ImageView ebookDownload;
        public EbookViewAdapter(@NonNull View itemView) {
            super(itemView);
            ebookText=itemView.findViewById(R.id.ebookText);
            ebookDownload=itemView.findViewById(R.id.ebookDownload);
        }
    }
}
