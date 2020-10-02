package com.example.studentsityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.studentsityapp.Adapters.TimeTableAdapter;
import com.example.studentsityapp.UserHelperclass.Timetable;
import com.example.studentsityapp.UserHelperclass.TimetableHC;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FacTimeTableDisplay extends AppCompatActivity {

    private RecyclerView recyclerViewtt;
    TimeTableAdapter dataAdapter;
    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_time_table_display);

        recyclerViewtt=findViewById(R.id.timetablerv);
        SimpleDateFormat sdf=new SimpleDateFormat("EEEE");
        Date d=new Date();
        String dayofweek=sdf.format(d);

        final ArrayList<Timetable> arrayList=new ArrayList<>();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Class1").document(dayofweek).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot snapshot=task.getResult();
                    if(snapshot.exists())
                    {
                        TimetableHC timetablehc=snapshot.toObject(TimetableHC.class);
                        arrayList.add(new Timetable("Period",timetablehc.getPeriod1()));
                        arrayList.add(new Timetable("Period",timetablehc.getPeriod2()));
                        arrayList.add(new Timetable("Period",timetablehc.getPeriod3()));

                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(FacTimeTableDisplay.this);
                        recyclerViewtt.setLayoutManager(layoutManager);
                        dataAdapter=new TimeTableAdapter(FacTimeTableDisplay.this,arrayList);
                        recyclerViewtt.setAdapter(dataAdapter);
                    }
                    else
                    {
                        Toast.makeText(FacTimeTableDisplay.this,"Some problrm in RV",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}