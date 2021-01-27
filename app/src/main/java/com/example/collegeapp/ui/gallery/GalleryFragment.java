package com.example.collegeapp.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {
    
    private RecyclerView indepeRecycler,convoRecycler;
    Gallery_adapter adapter;
    DatabaseReference reference;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_gallery, container, false);
        reference=FirebaseDatabase.getInstance().getReference().child("gallery");
        
        indepeRecycler=view.findViewById(R.id.indepeRecycler);
        convoRecycler=view.findViewById(R.id.convocaRecycler);


        
        getConvoImage();
        
        getIndepeImage();
        
        return view;
    }

    private void getIndepeImage() {
        reference.child("Independence Day").addValueEventListener(new ValueEventListener() {

            List<String> imageList=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String data=(String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter=new Gallery_adapter(getContext(),imageList);

               indepeRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
               indepeRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getConvoImage() {
        reference.child("convocation").addValueEventListener(new ValueEventListener() {

            List<String> imageList=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String data=(String) snapshot.getValue();
                    imageList.add(data);
                }

                adapter=new Gallery_adapter(getContext(),imageList);

                convoRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));

                convoRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
