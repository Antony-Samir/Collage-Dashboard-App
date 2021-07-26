package com.example.collagedashboardapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AllStudentsActivity extends AppCompatActivity {
    ListView studentList;
    ArrayAdapter<String> StudentAdapter;
    StudentsData students;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);



        ToDo();

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(AllStudentsActivity.this, com.example.collagedashboardapplication.StudentDetails.class);
                cursor.moveToPosition(i);
                intent.putExtra("StudentID",cursor.getInt(0));
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        ToDo();
    }

    private void ToDo() {
        studentList = findViewById(R.id.ShowStud);
        StudentAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        studentList.setAdapter(StudentAdapter);

        try {
            students = new StudentsData(getApplicationContext());
            cursor= students.getAllStudents();
            while (!cursor.isAfterLast())
            {
                StudentAdapter.add(cursor.getString(1));
                cursor.moveToNext();
            }

        }
        catch (Exception e) {
            Toast.makeText(this, "No Students", Toast.LENGTH_LONG).show();
        }


        Button add = findViewById(R.id.button5);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(AllStudentsActivity.this, com.example.collagedashboardapplication.AddStudent.class);
                startActivity(i);
            }
        });
    }
}


