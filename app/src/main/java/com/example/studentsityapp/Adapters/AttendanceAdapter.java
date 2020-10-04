package com.example.studentsityapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsityapp.R;

import java.util.ArrayList;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> namelist;

    public AttendanceAdapter() {
    }


    private ArrayList<String> presentstuds;

    public AttendanceAdapter(Context context, ArrayList<String> namelist) {
        this.context = context;
        this.namelist = namelist;
        presentstuds=new ArrayList<>();
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.attendancetemplate, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.studname.setText(namelist.get(position));
        holder.studname.setTextColor(Color.parseColor("#FF0000"));
        holder.present.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked())
                {
                    holder.studname.setTextColor(Color.parseColor("#008000"));
                    presentstuds.add(holder.studname.getText().toString());
                }
                else if(!buttonView.isChecked())
                {
                    holder.studname.setTextColor(Color.parseColor("#FF0000"));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView studname;
        CheckBox present;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            studname=itemView.findViewById(R.id.studentname_attendance);
            present=itemView.findViewById(R.id.present_cb);
        }
    }
}
