package com.example.adiutor_app.Data_Getter.Notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.adiutor_app.Entities.Notification;
import com.example.adiutor_app.R;
import java.util.ArrayList;

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.MyViewHolder> {
//Creating Variables that will be intialized later with the data that will be

    ArrayList<Notification> notificationsList;
    Context context;
    int imgs[];
    Notification noti;

    public Notification_Adapter(Context ct, ArrayList<Notification> notificationsList,int images[]) {
        //here we set the  previous data we intialized to the Adapter Parameters that be declared into the MainActivity which is "notifications.java"
        context = ct;
        imgs= images;
        this.notificationsList = notificationsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Here we choose to inflate the data from this class and in what design we created
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.students_notification_designrow, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Here we set the Design to its variables that we want it to be displayed
        holder.txt1.setText(notificationsList.get(position).getDomain());
        holder.txt2.setText(notificationsList.get(position).getBody());
        holder.logo.setImageResource(R.drawable.academy_logo);
        holder.img.setImageResource(imgs[position]);

    }

    @Override
    public int getItemCount() {
        //here we return the items to its array length
        return notificationsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt1, txt2;
        ImageView img;
        ImageView logo;
        String url;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // here we declaring a variables of the design that we created to be inflated by the recyclerView
            noti = new Notification();
            txt1 = itemView.findViewById(R.id.domain_txt);
            txt2 = itemView.findViewById(R.id.des_txt);
            img = itemView.findViewById(R.id.not_img);
            logo = itemView.findViewById(R.id.imageView3);
        }
    }

}
