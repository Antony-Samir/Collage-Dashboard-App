package com.example.collagedashboardapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collagedashboardapplication.Data.AttendanceData;
import com.example.collagedashboardapplication.Data.CoursesData;
import com.example.collagedashboardapplication.Data.Student;
import com.example.collagedashboardapplication.Data.StudentsData;

public class StudentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        StudentsData stud=new StudentsData(this);
        int studentId = getIntent().getExtras().getInt("StudentID");
        Student student = stud.getById(studentId);

        TextView IdText = findViewById(R.id.DetailsStudentID);
        IdText.setText("ID: " + student.getId());
        TextView NameText = findViewById(R.id.detailsName);
        NameText.setText("Name: " + student.getName());
        TextView dateText = findViewById(R.id.detailsDate);
        dateText.setText("BirthDate: " + student.getBirthdate());
        TextView CoursesText = findViewById(R.id.DetailsCourses);

        String s = "Courses: ";
        try
        {
            AttendanceData ad = new AttendanceData(this);
            Cursor c = new CoursesData(this).getAllCourses();
            while (!c.isAfterLast())
            {
                if (ad.getCount(studentId, c.getInt(0)) > -1)
                {
                    s += c.getString(1) + ", ";
                }
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No courses!", Toast.LENGTH_LONG).show();
        }

        CoursesText.setText(s);
    }
}
