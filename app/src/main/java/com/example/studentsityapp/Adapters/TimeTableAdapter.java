package com.example.studentsityapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsityapp.FacAttendanceActivity;
import com.example.studentsityapp.R;
import com.example.studentsityapp.UserHelperclass.Timetable;


import java.sql.Time;
import java.util.ArrayList;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.ViewHolder2> {

    Context context;
    ArrayList<Timetable> arrayList;

    public TimeTableAdapter(Context context, ArrayList<Timetable> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.timetabletemplate,parent,false);
        ViewHolder2 viewHolder=new ViewHolder2(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        holder.period.setText(arrayList.get(position).getPeriod());
        holder.periodName.setText(arrayList.get(position).getPeriodName());
        holder.periodName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, FacAttendanceActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder
    {
        private TextView period,periodName;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            period=itemView.findViewById(R.id.period);
            periodName=itemView.findViewById(R.id.period_name);
        }
    }

}
