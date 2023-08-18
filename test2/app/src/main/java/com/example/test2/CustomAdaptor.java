package com.example.test2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {

    private Context context;
    private ArrayList TYPE, TIME,DATE,DESCRIPTION,id,tab;
    int position;
    Activity activity;
    CustomAdaptor(Activity activity,
            Context context,
                  ArrayList TYPE,
                  ArrayList TIME,
                  ArrayList DATE,
                  ArrayList DESCRIPTION,
                  ArrayList id,
                  ArrayList tab){
        this.activity = activity;
        this.context = context;
        this.TIME = TIME;
        this.TYPE = TYPE;
        this.DATE = DATE;
        this.DESCRIPTION = DESCRIPTION;
        this.id  = id;
        this.tab = tab;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.type_text.setText(String.valueOf(TYPE.get(position)));
        holder.time_text.setText(String.valueOf(TIME.get(position)));
        holder.date_text.setText(String.valueOf(DATE.get(position)));
        holder.des_text.setText(String.valueOf(DESCRIPTION.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("type",String.valueOf(TYPE.get(position)));
                intent.putExtra("time",String.valueOf(TIME.get(position)));
                intent.putExtra("date",String.valueOf(DATE.get(position)));
                intent.putExtra("des",String.valueOf(DESCRIPTION.get(position)));
                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("tab",String.valueOf(tab.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return TYPE.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView type_text,time_text,date_text,des_text;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            type_text = itemView.findViewById(R.id.title_text);
            time_text = itemView.findViewById(R.id.time_text);
            date_text = itemView.findViewById(R.id.date_text);
            des_text = itemView.findViewById(R.id.des_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
