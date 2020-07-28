package com.example.leaveform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PreviousRecordActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    public int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_record);

        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        String uid=firebaseUser.getUid();
        recyclerView = findViewById(R.id.recyclerView);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("leavehistory").child(uid);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int childrencount=(int)dataSnapshot.getChildrenCount();
                History[] history=new History[childrencount];
                HistoryLinkedlist historyLinkedlist=new HistoryLinkedlist();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String db_from = String.valueOf(ds.child("From").getValue());
                    String db_to = String.valueOf(ds.child("To").getValue());
                    String db_reason = String.valueOf(ds.child("Reason").getValue());
                    history[counter]=new History(db_from,db_to,db_reason);
                    historyLinkedlist.insertItem(history[counter]);
                }

                recyclerAdapter=new RecyclerAdapter(historyLinkedlist,childrencount);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}