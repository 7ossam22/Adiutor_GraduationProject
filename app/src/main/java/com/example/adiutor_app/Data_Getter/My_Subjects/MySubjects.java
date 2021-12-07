package com.example.adiutor_app.Data_Getter.My_Subjects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.adiutor_app.Database_Connection.VolleySingleton;
import com.example.adiutor_app.Entities.ContentData;
import com.example.adiutor_app.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySubjects extends AppCompatActivity {
    HashMap<String ,String > hashMap;
    String  Id;
    RecyclerView recyclerView;
    Button content,sheets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!isTaskRoot()) {
            final Intent intent = getIntent();
            final String intentAction = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) &&
                    intentAction != null && intentAction.equals(Intent.ACTION_MAIN)) {
                finish();
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_subjects);
        hashMap = new HashMap<>();
        content = findViewById(R.id.content_btn);
        sheets = findViewById(R.id.sheets_btn);
        Intent intent = getIntent();
        Integer ID = intent.getIntExtra("courseID",-1);
        Id = Integer.toString(ID);
        recyclerView = findViewById(R.id.datashow_rv);
    }


    public void selectContent(final String tableName, HashMap<String, String> conditions) {
        Gson gson = new Gson();
        final String con = gson.toJson(conditions);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://adiutorgp.000webhostapp.com/selectContent.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error")) {
                        System.out.println(obj.getString("message"));

                    } else {
                        final ArrayList<ContentData> courses = new ArrayList<>();
                        JSONArray arr = obj.getJSONArray("content");
                        for (int i = 0; i < arr.length(); i++) {
                            final JSONObject contentobject = (JSONObject) arr.get(i);
                            final ContentData contentData = new ContentData();
                            contentData.setID(contentobject.getInt("ID"));
                            contentData.setName(contentobject.getString("Name"));
                            contentData.setUrl(contentobject.getString("Url"));
                            contentData.setType(contentobject.getString("contentType"));
                            courses.add(contentData);
                        }
                        Subject_Adapter adapter = new Subject_Adapter(getApplicationContext(),courses);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "No Data Available", Toast.LENGTH_LONG).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), "" + error, Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("table", tableName);
                param.put("conditions", con);
                return param;
            }
        };

        VolleySingleton.getnInstance(this).addRequestQue(stringRequest);
    }

    public void back_button(View view) {
        onBackPressed();
    }

    public void contentbtn(View view) {
        hashMap.clear();
        hashMap.put("LectureID",Id);
        hashMap.put("contentType","others");
        selectContent("content",hashMap);
        content.setBackgroundResource(R.drawable.btn_shape);
        sheets.setBackgroundResource(R.drawable.btn_shape2);
    }

    public void sheetsbtn(View view) {

        hashMap.clear();
        hashMap.put("LectureID",Id);
        hashMap.put("contentType","sheets");
        selectContent("content",hashMap);

        content.setBackgroundResource(R.drawable.btn_shape2);
        sheets.setBackgroundResource(R.drawable.btn_shape);
    }
}
