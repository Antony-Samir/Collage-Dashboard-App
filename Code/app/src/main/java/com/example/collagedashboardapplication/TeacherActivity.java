package com.example.collagedashboardapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collagedashboardapplication.Data.Course;
import com.example.collagedashboardapplication.Data.CoursesData;

public class TeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        CoursesData CD = new CoursesData(getApplicationContext());
        ListView myList = findViewById(R.id.teacherCourseList);
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        myList.setAdapter(listAdapter);

        try
        {
            Course[] course = CD.allCourses();

            for (int i =0; i<course.length; i++)
            {
                listAdapter.add(course[i].getName());
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), "fadyyyyyyyyyyy", Toast.LENGTH_LONG).show();
        }



        /*listAdapter.add("Test1");
        listAdapter.add("Test2");
        listAdapter.add("Test3");
        listAdapter.add("Test4");*/

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                Toast.makeText(getApplicationContext(), ((TextView)arg1).getText().toString(), Toast.LENGTH_LONG).show();

                Intent i = new Intent(TeacherActivity.this, com.example.collagedashboardapplication.AttendaceActivity.class);
                startActivity(i);
            }
        });

    }
}
