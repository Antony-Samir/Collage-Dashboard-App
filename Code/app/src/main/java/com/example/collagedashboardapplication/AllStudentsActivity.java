package com.example.collagedashboardapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collagedashboardapplication.Data.StudentsData;

public class AllStudentsActivity extends AppCompatActivity {
    ListView studentList;
    ArrayAdapter<String> StudentAdapter;
    StudentsData students;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);


        studentList = findViewById(R.id.showAllStudents);
        StudentAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        studentList.setAdapter(StudentAdapter);

        registerForContextMenu(studentList);

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


        Button add = findViewById(R.id.newStudentBtn);
        add.setOnClickListener(v -> {
            Intent i = new Intent(AllStudentsActivity.this, AddStudentActivity.class);
            startActivity(i);
        });


        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(AllStudentsActivity.this, StudentDetailsActivity.class);
                cursor.moveToPosition(i);
                intent.putExtra("StudentID",cursor.getInt(0));
                startActivity(intent);
            }
        });


    }

    @Override
    public void onCreateContextMenu (ContextMenu menu, View v,
                                     ContextMenu.ContextMenuInfo menuInfo){

        getMenuInflater().inflate(R.menu.update_delete_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected (MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String selectedMovie = ((TextView) info.targetView).getText().toString();

        int id = item.getItemId();
        if (id == R.id.item_delete) {
            //movieAdapter.remove(selectedMovie);
            //movies.deleteMovie(selectedMovie);
            return true;
        }
        if (id == R.id.item_update) {
            //Intent i = new Intent(AllMoviesActivity.this, UpdateMovieActivity.class);
            //i.putExtra("movieName", selectedMovie);
            //i.putExtra("movieDesc", movies.getMovieDescription(selectedMovie));
            //startActivity(i);
            return true;

        }
        return super.onContextItemSelected(item);
    }
}


