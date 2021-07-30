package com.example.collagedashboardapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collagedashboardapplication.Data.AttendanceData;
import com.example.collagedashboardapplication.Data.Course;
import com.example.collagedashboardapplication.Data.CoursesData;
import com.example.collagedashboardapplication.Data.StudentsData;

public class CourseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_course_details);
        CoursesData cdata=new CoursesData(getApplicationContext());
        int courseId = getIntent().getExtras().getInt("id");
        Course course = cdata.getById(courseId);

        TextView c_id = findViewById(R.id.id_details);
        TextView c_name = findViewById(R.id.name_details);
        TextView c_creditH = findViewById(R.id.creditH_delails);
        TextView c_bookTitle = findViewById(R.id.bookTitle_details);
        TextView c_students = findViewById((R.id.student_details));

        c_id.setText("Id: " + " " + course.getId());
        c_name.setText("Name: " + " " + course.getName());
        c_creditH.setText("Credit Hours: " + " " + course.getHours());
        c_bookTitle.setText("Book Title: " + " " + course.getBook());
        String str = "Student";
        try
        {
            AttendanceData obj = new AttendanceData(this);

            Cursor c = new StudentsData(this).getAllStudents();
            while (!c.isAfterLast())
            {
                if (obj.getCount(courseId, c.getInt(0)) > -1)
                {
                    str += c.getString(1) + ", ";
                }
            }
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"No Students!",Toast.LENGTH_LONG).show();
        }
        c_students.setText(str);


    }
}
