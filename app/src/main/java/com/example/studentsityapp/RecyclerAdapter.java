package com.example.studentsityapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{

    HistoryLinkedlist linkedlist;
    int childcount;

    public RecyclerAdapter(HistoryLinkedlist linkedlist, int childcount) {
        this.linkedlist = linkedlist;
        this.childcount = childcount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.row_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.fromm.setText(linkedlist.getHistory(position).getFrom());
        holder.too.setText(linkedlist.getHistory(position).getTo());
        holder.reasonn.setText(linkedlist.getHistory(position).getReason());

    }

    @Override
    public int getItemCount() {
        return childcount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView fromm,too,reasonn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fromm=itemView.findViewById(R.id.his_from_date);
            too=itemView.findViewById(R.id.his_to_date);
            reasonn=itemView.findViewById(R.id.his_reason_da);

        }
    }
}