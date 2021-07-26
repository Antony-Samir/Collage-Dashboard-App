package com.example.collagedashboardapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collagedashboardapplication.Data.Course;
import com.example.collagedashboardapplication.Data.CoursesData;

public class AddCoursesActivity extends AppCompatActivity {

    CoursesData cdata;
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);

        Button add_course=(Button)findViewById(R.id.addCourseButton);
        final EditText course_id=(EditText)findViewById(R.id.id_tf);
        final EditText course_name=(EditText)findViewById(R.id.name_tf);
        final EditText course_creditH=(EditText)findViewById(R.id.bookTitle_tf);
        final EditText course_Book=(EditText)findViewById(R.id.creditH_tf);
        cdata=new CoursesData(getApplicationContext());

        add_course.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                course=new Course(Integer.parseInt(course_id.getText().toString()),course_name.getText().toString(),Integer.parseInt(course_creditH.getText().toString()),course_Book.getText().toString());

                cdata.add(course);
                course_id.getText().clear();
                course_name.getText().clear();
                course_creditH.getText().clear();
                course_Book.getText().clear();

            }
        });

    }
}
