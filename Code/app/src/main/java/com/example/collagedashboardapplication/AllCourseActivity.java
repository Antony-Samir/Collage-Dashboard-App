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

import androidx.appcompat.app.AppCompatActivity;

import com.example.collagedashboardapplication.Data.CoursesData;

public class AllCourseActivity extends AppCompatActivity {

    CoursesData cdata;
    Cursor cursor;
    ArrayAdapter<String> listAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_course);

        ListView list = (ListView)findViewById(R.id.listView);
        cdata = new CoursesData(getApplicationContext());
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        list.setAdapter(listAdapter);

        registerForContextMenu(list);

        try
        {
            cursor = cdata.getAllCourses();
            while (!cursor.isAfterLast())
            {
                listAdapter.add(cursor.getString(1));
                cursor.moveToNext();
            }
        }
        catch(Exception e){

        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent (AllCourseActivity.this, CourseDetailsActivity.class);
                cursor.moveToPosition(i);
                intent.putExtra("id",cursor.getInt(0));
                startActivity(intent);

            }
        });


        Button add_btn = (Button)findViewById(R.id.newCourseBtn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_course = new Intent(AllCourseActivity.this, AddCoursesActivity.class);
                startActivity(add_course);
            }
        });


    }

    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        getMenuInflater().inflate(R.menu.update_delete_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected (MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String selectedCourse = ((TextView) info.targetView).getText().toString();

        int id = item.getItemId();
        if (id == R.id.item_delete) {
            listAdapter.remove(selectedCourse);
            cdata.delete(selectedCourse);
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
