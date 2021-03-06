package com.example.collagedashboardapplication;

import android.content.Intent;
import android.database.Cursor;
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

    CoursesData cdata;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        cdata = new CoursesData(getApplicationContext());
        ListView myList = findViewById(R.id.teacherCourseList);
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        myList.setAdapter(listAdapter);

        try
        {
            cursor = cdata.getAllCourses();
            while (!cursor.isAfterLast())
            {
                listAdapter.add(cursor.getString(1));
                cursor.moveToNext();
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

                Intent i = new Intent(TeacherActivity.this, EnrollActivity.class);
                startActivity(i);
            }
        });

    }
}
