package com.example.studentsityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.studentsityapp.Adapters.AttendanceAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FacAttendanceActivity extends AppCompatActivity {

    RecyclerView attendanceRV;
    FirebaseFirestore firestore;
    ArrayList<String> names;
    DatabaseReference reference;
    String dept;
    Map<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_attendance);

        reference = FirebaseDatabase.getInstance().getReference().child("users").child("Faculty").child(FirebaseAuth.getInstance().getUid()).child("department");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dept = String.valueOf(snapshot.getValue());
                System.out.println(dept.replaceAll("\\s", ""));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        attendanceRV = findViewById(R.id.attendance_RV);
        firestore = FirebaseFirestore.getInstance();
        names = new ArrayList<>();
        firestore=FirebaseFirestore.getInstance();
//        firestore.collection("Students").document("CSE").get()
//                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
//                                       {
//                                           @Override
//                                           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                               if (task.isSuccessful()) {
//                                                   DocumentSnapshot snapshot = task.getResult();
//                                                   if (snapshot.exists()) {
//                                                       System.out.println(snapshot.toString());
//                                                       map = new HashMap<>();
//                                                       map = snapshot.getData();
//                                                   }
//                                               }
//                                               else {
//                                                   Toast.makeText(FacAttendanceActivity.this,"problm",Toast.LENGTH_SHORT).show();
//                                               }
//                                           }
//                                       });
//
//        System.out.println(map.toString());

        names.add("Vibin");
        names.add("Vignesh");
        names.add("Harish");
        names.add("Srinath");

        AttendanceAdapter adapter = new AttendanceAdapter(FacAttendanceActivity.this,names);
        attendanceRV.setHasFixedSize(true);
        attendanceRV.setLayoutManager(new LinearLayoutManager(this));
        attendanceRV.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.attendanceactivitymenu,menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.submitbtn) {
//            ArrayList<String> present = new AttendanceAdapter().getPresentstuds();
//            System.out.println(present.toString());
//        }
//        return true;
//    }
}