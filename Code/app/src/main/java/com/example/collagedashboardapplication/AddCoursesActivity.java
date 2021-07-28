package com.example.collagedashboardapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        Button add_course = (Button)findViewById(R.id.addCourseBtn);
        final EditText course_id = (EditText)findViewById(R.id.courseIdTxt);
        final EditText course_name = (EditText)findViewById(R.id.courseNameTxt);
        final EditText course_creditH = (EditText)findViewById(R.id.bookTitleTxt);
        final EditText course_Book = (EditText)findViewById(R.id.courseHoursTxt);
        cdata = new CoursesData(getApplicationContext());

        add_course.setOnClickListener(v -> {
            if(course_id.getText().toString().isEmpty() || course_name.getText().toString().isEmpty() || course_creditH.getText().toString().isEmpty() || course_Book.getText().toString().isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Enter All Fields!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                course = new Course(Integer.parseInt(course_id.getText().toString()), course_name.getText().toString(), Integer.parseInt(course_creditH.getText().toString()), course_Book.getText().toString());

                cdata.add(course);
                Toast.makeText(getApplicationContext(), "Course Added Successfully", Toast.LENGTH_SHORT).show();

                course_id.getText().clear();
                course_name.getText().clear();
                course_creditH.getText().clear();
                course_Book.getText().clear();

            }
        });


    }
}
