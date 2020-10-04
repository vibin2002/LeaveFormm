package com.example.studentsityapp.UserHelperclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studentsityapp.FacAttendanceActivity;
import com.example.studentsityapp.FacTimeTableDisplay;
import com.example.studentsityapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FacDashboard extends AppCompatActivity {

    private CardView takeattendance,viewattendance,addStudent,addClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_dashboard);

        takeattendance=findViewById(R.id.takeattendace_cv);
        takeattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacDashboard.this,FacTimeTableDisplay.class));
            }
        });

    }
}