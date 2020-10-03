package com.example.studentsityapp.UserHelperclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studentsityapp.FacTimeTableDisplay;
import com.example.studentsityapp.R;

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
                startActivity(new Intent(FacDashboard.this, FacTimeTableDisplay.class));
            }
        });

    }
}