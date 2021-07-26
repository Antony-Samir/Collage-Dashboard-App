package com.example.collagedashboardapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.collagedashboardapplication.Data.Student;
public class AddStudent extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_student);
		Button add_stu=(Button)findViewById(R.id.button1);
		final EditText name=(EditText)findViewById(R.id.Stud_name);
		final EditText date=(EditText)findViewById(R.id.bookTitle_tf);
		
		add_stu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String n=name.getText().toString();
				String d=date.getText().toString();
				Student stud = new Student(0,n,d);
				new StudentsData(getApplicationContext()).add(stud);
				name.setText("");
				date.setText("");
				
			}
		});
	}

}
