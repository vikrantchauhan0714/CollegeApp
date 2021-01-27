package com.example.collegeapp.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {
    private RecyclerView csDepartment,physicsDepartment,mechDepartment,chemDepartment;
    private LinearLayout csNoData,mcNoData,physicsNoData,chemNoData;
    private List<TeacherData> list1,list2,list3,list4;
    private ProgressBar progressBar1,progressBar2,progressBar3,progressBar4;

    private DatabaseReference reference,dbRef;
    TeacherAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);
        csDepartment = view.findViewById(R.id.csDepartment);
        physicsDepartment = view.findViewById(R.id.physDepartment);
        mechDepartment = view.findViewById(R.id.mechDepartment);
        chemDepartment = view.findViewById(R.id.chemDepartment);

        progressBar1=view.findViewById(R.id.progress1);
        progressBar2=view.findViewById(R.id.progress2);
        progressBar3=view.findViewById(R.id.progress3);
        progressBar4=view.findViewById(R.id.progress4);
        //not data
        csNoData = view.findViewById(R.id.csNoData);
        mcNoData = view.findViewById(R.id.mcNoData);
        physicsNoData = view.findViewById(R.id.physicsNoData);
        chemNoData = view.findViewById(R.id.chemNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        csDepartment();
        mechDepartment();
        physicsDepartment();
        chemDepartment();


        return view;


    }

    private void csDepartment() {
        dbRef=reference.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1=new ArrayList<>();
                if(!dataSnapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                }else{
                    csNoData.setVisibility(View.GONE);
                    progressBar1.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list1.add(data);

                    }

                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(getContext()));

                    adapter=new TeacherAdapter(list1,getContext());
                    csDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mechDepartment() {
        dbRef=reference.child("Mechanical");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2=new ArrayList<>();
                if(!dataSnapshot.exists()){
                    mcNoData.setVisibility(View.VISIBLE);
                    mechDepartment.setVisibility(View.GONE);
                }else{
                    mcNoData.setVisibility(View.GONE);
                    progressBar2.setVisibility(View.GONE);
                    mechDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list2.add(data);

                    }

                    mechDepartment.setHasFixedSize(true);
                    mechDepartment.setLayoutManager(new LinearLayoutManager(getContext()));

                    adapter=new TeacherAdapter(list2,getContext());
                    mechDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void physicsDepartment() {
        dbRef=reference.child("Physics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3=new ArrayList<>();
                if(!dataSnapshot.exists()){
                    physicsNoData.setVisibility(View.VISIBLE);
                    physicsDepartment.setVisibility(View.GONE);
                }else{
                    physicsNoData
                            .setVisibility(View.GONE);
                    progressBar3.setVisibility(View.GONE);
                    physicsDepartment .setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list3.add(data);

                    }

                    physicsDepartment.setHasFixedSize(true);
                    physicsDepartment.setLayoutManager(new LinearLayoutManager(getContext()));

                    adapter=new TeacherAdapter(list3,getContext());
                    physicsDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void chemDepartment() {
        dbRef=reference.child("Chemistry");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4=new ArrayList<>();
                if(!dataSnapshot.exists()){
                    chemNoData.setVisibility(View.VISIBLE);
                    chemDepartment.setVisibility(View.GONE);
                }else{
                    chemNoData.setVisibility(View.GONE);

                    chemDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list4.add(data);

                    }

                    chemDepartment.setHasFixedSize(true);
                    chemDepartment.setLayoutManager(new LinearLayoutManager(getContext()));

                    adapter=new TeacherAdapter(list4,getContext());

                    chemDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}



