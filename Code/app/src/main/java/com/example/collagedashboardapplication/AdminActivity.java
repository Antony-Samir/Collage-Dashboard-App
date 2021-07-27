package com.example.collagedashboardapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button coursesBtn = findViewById(R.id.coursesBtn);
        Button studentsBtn = findViewById(R.id.studentsBtn);

        coursesBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View V)
            {
                Intent i = new Intent(AdminActivity.this, CourseActivity.class);
                startActivity(i);
            }
        });

        studentsBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View V)
            {
                Intent i = new Intent(AdminActivity.this, AllStudentsActivity.class);
                startActivity(i);
            }
        });
    }
}
