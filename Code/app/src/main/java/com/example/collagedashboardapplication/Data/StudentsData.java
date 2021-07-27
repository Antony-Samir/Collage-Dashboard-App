package com.example.collagedashboardapplication.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class StudentsData extends SQLiteOpenHelper {

    private static String dbname = "StudentsDb";
    private SQLiteDatabase StudentsDb;

    public StudentsData(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table students (id integer primary key autoincrement, name text not null, birthdate text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists students");
        onCreate(sqLiteDatabase);
    }

    public void add(com.example.collagedashboardapplication.Data.Student student) {

        ContentValues cv = new ContentValues();
        cv.put("name", student.getName());
        cv.put("birthdate", student.getBirthdate());
        StudentsDb = getWritableDatabase();
        StudentsDb.insert("students", null, cv);
        StudentsDb.close();

    }

    public Cursor getAllStudents() {

        StudentsDb = getReadableDatabase();
        String[] r = {"id", "name", "birthdate"};
        Cursor c = StudentsDb.query("students", r, null, null, null, null, null);
        if (c != null)
            c.moveToFirst();
        StudentsDb.close();
        return c;
    }

    public com.example.collagedashboardapplication.Data.Student[] allStudents() {

        ArrayList<com.example.collagedashboardapplication.Data.Student> list = new ArrayList<>();
        Cursor c = getAllStudents();
        while (!c.isAfterLast()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            String bd = c.getString(2);
            list.add(new com.example.collagedashboardapplication.Data.Student(id, name, bd));
            c.moveToNext();
        }
        return (com.example.collagedashboardapplication.Data.Student[]) list.toArray();

    }

    public void edit(com.example.collagedashboardapplication.Data.Student student) {

        ContentValues cv = new ContentValues();
        cv.put("name", student.getName());
        cv.put("birthdate", student.getBirthdate());
        StudentsDb.update("students", cv, "id=" + student.getId(), null);

    }

    public Boolean delete(int id) {

        return StudentsDb.delete("students", "id=" + id, null) > 0;
    }

    public com.example.collagedashboardapplication.Data.Student getById(int id) {

        StudentsDb = getReadableDatabase();
        String[] r = {"id", "name", "birthdate"};
        Cursor c = StudentsDb.query("students", r, "id=" + id, null, null, null, null);
        if (c != null)
            c.moveToFirst();
        StudentsDb.close();
        return new com.example.collagedashboardapplication.Data.Student(c.getInt(0), c.getString(1), c.getString(2));

    }

}
