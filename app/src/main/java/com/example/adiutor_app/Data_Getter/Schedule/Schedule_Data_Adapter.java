package com.example.adiutor_app.Data_Getter.Schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adiutor_app.R;

public class Schedule_Data_Adapter extends RecyclerView.Adapter<Schedule_Data_Adapter.MyViewHolder> {
    String data1[],data2[],data3[];
    int data4[];
    Context context;
    public Schedule_Data_Adapter(Context ct , String domain[],String date[],String description[],int schedule_image[] ) {
        context = ct;
        data1 = domain;
        data2 = date;
        data3 = description;
        data4 = schedule_image;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.schedule_desgin_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.domaintxt.setText(data1[position]);
        holder.datetxt.setText(data2[position]);
        holder.description.setText(data3[position]);
        holder.schedule_image.setImageResource(data4[position]);


    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView domaintxt,datetxt,description;
        ImageView schedule_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            domaintxt = itemView.findViewById(R.id.academy_name);
            datetxt = itemView.findViewById(R.id.message_date);
            description = itemView.findViewById(R.id.schedule_desc);
            schedule_image= itemView.findViewById(R.id.schedule_img);
        }
    }
}
