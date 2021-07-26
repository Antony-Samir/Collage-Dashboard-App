package com.example.collagedashboardapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addBtn = findViewById(R.id.button);




        addBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View V)
            {
                //Intent ii = new Intent(MainActivity.this, AllStudentsActivity.class);
                //startActivity(ii);

                String name = "Admin";
                int password = 123;
                Admin admin = new Admin(name, password);

                String name1 = "Teacher";
                int password1 = 159;
                Teacher teacher = new Teacher(name1, password1);

                String fName = ((EditText)findViewById(R.id.id_tf)).getText().toString();


                int sPassword = Integer.parseInt(((EditText)findViewById(R.id.editText)).getText().toString());

                if ((fName.equals(admin.name)) && (sPassword == admin.Password))
                {
                    Intent i = new Intent(MainActivity.this, AdminActivity.class);
                    startActivity(i);
                }
                else if ((fName.equals(teacher.name)) && (sPassword == teacher.Password))
                {
                    Intent i = new Intent(MainActivity.this, TeacherActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "User name or Password isn't correct", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}