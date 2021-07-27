package com.example.collagedashboardapplication.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

public class CoursesData extends SQLiteOpenHelper
{

    private static String dbname = "CoursesDb";
    private SQLiteDatabase CoursesDb;

    public CoursesData(Context context)
    {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sq)
    {
        sq.execSQL("create table courses (id integer primary key, name text not null, hours integer not null, book text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sq, int i, int i1)
    {
        sq.execSQL("drop table if exists courses");
        onCreate(sq);
    }

    public Cursor getAllCourses()
    {

        CoursesDb = getReadableDatabase();
        String[] r = {"id", "name", "hours", "book"};
        Cursor c = CoursesDb.query("courses", r, null, null, null, null, null);
        if (c != null)
            c.moveToFirst();
        CoursesDb.close();
        return c;
    }

    public void add(Course course)
    {
        ContentValues cv = new ContentValues();
        cv.put("id", course.getId());
        cv.put("name", course.getName());
        cv.put("hours", course.getHours());
        cv.put("book", course.getBook());
        CoursesDb = getWritableDatabase();
        CoursesDb.insert("courses", null, cv);
        CoursesDb.close();
    }

    public Course[] allCourses()
    {
        Vector<Course> vector = new Vector<>();
        Cursor c = getAllCourses();
        while (c.isAfterLast())
        {
            int id = c.getInt(0);
            String name = c.getString(1);
            int hours = c.getInt(2);
            String book = c.getString(3);
            vector.add(new Course(id, name, hours, book));
            c.moveToNext();
        }
        return (Course[]) vector.toArray();
    }

    public void edit(Course course)
    {
        ContentValues cv = new ContentValues();
        cv.put("name", course.getName());
        cv.put("hours", course.getHours());
        cv.put("book", course.getBook());
        CoursesDb.update("courses", cv, "id=" + course.getId(), null);
    }

    public Boolean delete(int id)
    {
        return CoursesDb.delete("courses", "id=" + id, null) > 0;
    }

    public Course getById(int id)
    {
        CoursesDb = getReadableDatabase();
        String[] r = {"id", "name", "hours", "book"};
        Cursor c = CoursesDb.query("courses", r, "id=" + id, null, null, null, null);
        if (c != null)
            c.moveToFirst();
        CoursesDb.close();
        return new Course(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3));
    }
}
