package com.example.collagedashboardapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collagedashboardapplication.Data.Student;
import com.example.collagedashboardapplication.Data.StudentsData;

public class AddStudentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_student);

		final EditText name = (EditText)findViewById(R.id.studentNameTxt);
		final EditText date = (EditText)findViewById(R.id.dateOfBirthTxt);


		Button add_stu = (Button)findViewById(R.id.addStudentBtn);
		add_stu.setOnClickListener(v -> {
			if(name.getText().toString().isEmpty())
			{
				Toast.makeText(getApplicationContext(), "Enter All Fields!", Toast.LENGTH_SHORT).show();
			}
			else {
				String n = name.getText().toString();
				String d = date.getText().toString();
				Student stud = new Student(0, n, d);
				new StudentsData(getApplicationContext()).createNewStudent(stud);
				name.setText("");
				date.setText("");
				Toast.makeText(getApplicationContext(), "Student Added Successfully", Toast.LENGTH_SHORT).show();

			}
		});



	}
}
