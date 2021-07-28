package com.example.collagedashboardapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collagedashboardapplication.Data.Admin;
import com.example.collagedashboardapplication.Data.Teacher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button addBtn = findViewById(R.id.loginBtn);

        addBtn.setOnClickListener(V -> {

            String adminName = "Admin";
            int adminPassword = 123;
            Admin admin = new Admin(adminName, adminPassword);

            String teacherName = "Teacher";
            int teacherPassword = 159;
            Teacher teacher = new Teacher(teacherName, teacherPassword);

            String username = ((EditText)findViewById(R.id.loginUsernameTxt)).getText().toString();
            int password = Integer.parseInt(((EditText)findViewById(R.id.loginPasswordTxt)).getText().toString());

            if ((username.equals(admin.name)) && (password == admin.Password))
            {
                Intent i = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(i);
            }
            else if ((username.equals(teacher.name)) && (password == teacher.Password))
            {
                Intent i = new Intent(MainActivity.this, TeacherActivity.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "User name or Password isn't correct", Toast.LENGTH_LONG).show();
            }
        });

    }
}