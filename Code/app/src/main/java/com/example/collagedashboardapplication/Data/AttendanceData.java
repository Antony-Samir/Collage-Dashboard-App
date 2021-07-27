package com.example.collagedashboardapplication.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AttendanceData extends SQLiteOpenHelper {

    private static String dbname = "AttendanceDb";
    private SQLiteDatabase AttendanceDb;

    public AttendanceData(Context context) {

        super(context, dbname, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table attendance (studentId integer not null, courseId integer not null, count integer not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists attendance");
        onCreate(db);

    }

    public void enroll(Student student, Course course) {

        ContentValues cv = new ContentValues();
        AttendanceDb = getWritableDatabase();
        cv.put("studentId", student.getId());
        cv.put("courseId", course.getId());
        cv.put("count", 0);
        AttendanceDb.insert("attendance", null, cv);
        AttendanceDb.close();

    }

    public int getCount(int studentId, int courseId) {

        AttendanceDb = getReadableDatabase();
        String[] col = {"studentId", "courseId", "count"};
        Cursor c = AttendanceDb.query("attendance", col,"studentId=" + studentId + " AND " + "courseId=" + courseId,null,null,null,null);
        if (c == null)
            return -1;
        c.moveToFirst();
        int i = c.getInt(2);
        AttendanceDb.close();
        return i;

    }

    public void updateCount(int studentId, int courseId) {

        int newCount = getCount(studentId, courseId) + 1;
        ContentValues cv = new ContentValues();
        cv.put("studentId", studentId);
        cv.put("courseId", courseId);
        cv.put("count", newCount);
        String whereClause = "studentId=" + studentId;
        whereClause += " AND courseId=" + courseId;
        AttendanceDb.update("attendance", cv, whereClause, null);

    }

    public boolean deleteStudentInCourse(int studentId,int courseId)
    {
        return AttendanceDb.delete("attendance","studentId=" + studentId + " AND " + "courseId=" + courseId,null) > 0;
    }

    public boolean deleteCourse(int id)
    {
        return AttendanceDb.delete("attendance","courseId=" + id,null) > 0;
    }

    public boolean deleteStudent(int id)
    {
        return AttendanceDb.delete("attendance","studentId=" + id,null) > 0;
    }
}
