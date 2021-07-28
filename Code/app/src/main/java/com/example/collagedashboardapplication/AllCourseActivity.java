package com.example.collagedashboardapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collagedashboardapplication.Data.CoursesData;

public class AllCourseActivity extends AppCompatActivity {

    CoursesData cdata;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_course);

        cdata=new CoursesData(getApplicationContext());
        ListView list = (ListView)findViewById(R.id.listView);
        final ArrayAdapter<String> listAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        list.setAdapter(listAdapter);

        try
        {
            cursor = cdata.getAllCourses();
            while (!cursor.isAfterLast())
            {
                listAdapter.add(cursor.getString(1));
                cursor.moveToNext();
            }
        }
        catch(Exception e){

        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent (AllCourseActivity.this, CourseDetailsActivity.class);
                cursor.moveToPosition(i);
                intent.putExtra("id",cursor.getInt(0));
                startActivity(intent);

            }
        });


        Button add_btn=(Button)findViewById(R.id.AddCourseBtn);
        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent add_course =new Intent(AllCourseActivity.this,AddCoursesActivity.class);
                startActivity(add_course);
            }
        });


    }
}
