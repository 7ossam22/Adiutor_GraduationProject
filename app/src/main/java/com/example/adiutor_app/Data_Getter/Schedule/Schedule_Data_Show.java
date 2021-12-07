package com.example.adiutor_app.Data_Getter.Schedule;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adiutor_app.R;

public class Schedule_Data_Show extends AppCompatActivity {
    RecyclerView recyclerView;
    String domain[] ={"Sha.Edu.eg","Sha.Edu.eg","Sha.Edu.eg"} , date[]={"24/12/2020","26/12/2020","30/12/2020"} , description[]={"Check Out your daily schedule from here->","Check Out your mid-Terms schedule from here ->","Check out your finals schedule from here ->"};
    int schedule_image[]={R.drawable.subject,R.drawable.subject,R.drawable.subject};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule__data__show);
        recyclerView = findViewById(R.id.schedule_RV);
        Schedule_Data_Adapter adapter = new Schedule_Data_Adapter(this,domain,date,description,schedule_image);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void back_button(View view) {
        onBackPressed();
    }
}
