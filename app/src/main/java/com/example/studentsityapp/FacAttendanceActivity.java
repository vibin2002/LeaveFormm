package com.example.studentsityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FacAttendanceActivity extends AppCompatActivity {

    RecyclerView attendanceRV;
    FirebaseFirestore firestore;
    public ArrayList<String> names;
    DatabaseReference reference,ref2;
    private String dept;
    Map<String, Object> map;
    TextView classname,subject,datetoday;
    private String TAG="Kadupu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_attendance);

        classname=findViewById(R.id.class_attendance);
        subject=findViewById(R.id.subject_attendance);
        datetoday=findViewById(R.id.date_attendance);
        names = new ArrayList<>();
        attendanceRV = findViewById(R.id.attendance_RV);

        Date d = new Date();                                                            //Date
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        String today=s.toString();
        datetoday.setText(today);

        Intent intent=getIntent();
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
                            //System.out.println("Student"+studname);
                        }
                        System.out.println(names.toString());

                        names.add("Vibin");
                        names.add("Vignesh");
                        names.add("Harish");
                        names.add("Srinath");
                        AttendanceAdapter adapter = new AttendanceAdapter(FacAttendanceActivity.this,names);
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

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.submitbtn) {
//            ArrayList<String> present = new AttendanceAdapter().getPresentstuds();
//            System.out.println(present.toString());
//        }
//        return true;
//    }
}