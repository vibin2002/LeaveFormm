package com.example.studentsityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentsityapp.Adapters.AttendanceAdapter;
import com.example.studentsityapp.Adapters.PresentStudents;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FacAttendanceActivity extends AppCompatActivity {

    RecyclerView attendanceRV;
    FirebaseFirestore firestore;
    public ArrayList<String> names;
    DatabaseReference reference,ref2;
    private String dept,subjectname;
    Map<String,Integer> map;
    TextView classname,subject,datetoday;
    private String TAG="Kadupu";
    private String today;
    private AttendanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_attendance);

        classname=findViewById(R.id.class_attendance);
        subject=findViewById(R.id.subject_attendance);
        datetoday=findViewById(R.id.date_attendance);
        names = new ArrayList<>();
        map=new HashMap<>();
        attendanceRV = findViewById(R.id.attendance_RV);
        firestore=FirebaseFirestore.getInstance();

        Date d = new Date();                                                            //Date
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        today=s.toString();
        datetoday.setText(today);

        Intent intent=getIntent();
        subjectname=intent.getStringExtra("periodName");
        subject.setText(intent.getStringExtra("periodName"));

        reference = FirebaseDatabase.getInstance().getReference().child("users").child("Faculty").child("CSE").child(FirebaseAuth.getInstance().getUid()).child("department");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dept = String.valueOf(snapshot.getValue());
                System.out.println("Department"+dept.replaceAll("\\s", ""));
                classname.setText(dept);

                ref2=FirebaseDatabase.getInstance().getReference().child("users").child("Student").child(dept);
                ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds:snapshot.getChildren())
                        {
                            names.add(String.valueOf(ds.child("name").getValue()));
                            map.put(String.valueOf(ds.child("name").getValue()),0);
                            //System.out.println("Student"+studname);
                        }
                        System.out.println(names.toString());

                        adapter = new AttendanceAdapter(FacAttendanceActivity.this,names,map);
                        attendanceRV.setHasFixedSize(true);
                        attendanceRV.setLayoutManager(new LinearLayoutManager(FacAttendanceActivity.this));
                        attendanceRV.setAdapter(adapter);
                        attendanceRV.addItemDecoration(new DividerItemDecoration(attendanceRV.getContext(), DividerItemDecoration.VERTICAL));

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.attendanceactivitymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.submitbtn) {
            PresentStudents presentstuds =adapter.getPresentStudents();
            Map<String, Integer> studsmap = presentstuds.getMap();
            System.out.println("Present ="+studsmap);
            firestore.collection("Attendance").document(dept).collection(subjectname).document(today).set(studsmap);
        }
        return true;
    }
}