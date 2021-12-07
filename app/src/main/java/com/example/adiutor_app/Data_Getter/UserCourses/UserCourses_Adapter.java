package com.example.adiutor_app.Data_Getter.UserCourses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adiutor_app.Data_Getter.My_Subjects.MySubjects;
import com.example.adiutor_app.Entities.CoursesData;
import com.example.adiutor_app.R;

import java.util.ArrayList;


public class UserCourses_Adapter extends RecyclerView.Adapter<UserCourses_Adapter.MyViewHolder> {
    Context context;
    ArrayList<CoursesData> arr;

    public UserCourses_Adapter(Context ct, ArrayList<CoursesData> arr) {
        context = ct;
        this.arr = arr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.courses_data_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //CoursesData coursesData = new CoursesData();
        holder.course_name.setText(arr.get(position).getName());
        holder.course_grade.setText(" Place " + arr.get(position).getPlace());
        holder.course_image.setImageResource(R.drawable.courses);
        holder.lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(arr.get(position).getPlace());
                Intent intent = new Intent(context, MySubjects.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("courseID", arr.get(position).getID());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView course_name, course_grade;
        ImageView course_image;
        LinearLayout lo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            course_name = itemView.findViewById(R.id.name_tv);
            course_grade = itemView.findViewById(R.id.grade_tv);
            course_image = itemView.findViewById(R.id.course_img);
            lo = itemView.findViewById(R.id.courses_parentLO);
        }

    }
}
