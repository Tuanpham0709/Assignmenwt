package com.example.assignmentfinal.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.assignmentfinal.StudentManager;
import com.example.assignmentfinal.model.ClassRoom;
import com.example.assignmentfinal.model.StudentClass;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudentDBManager extends SQLiteOpenHelper {
    private  String TAG = "StudentDBManager";
    private  static final String DATABASE_NAME = "studentpolytechnic";
    private  static final String TABLE_CLASS = "class";
    private  static final String TABLE_STUDENT = "student";
    private  static final String ID = "id";
    private  static final String STUDENT_NAME = "student_name";
    private  static final String CLASS_NAME = "class_name";
    private  static final String BIRTDAY = "birtday";
    private  static final String CLASS_CODE = "classcode";
    private  String SQLQUERY_STUDENT = " CREATE TABLE " + TABLE_STUDENT + "( " +
            ID + " integer primary key, " +
            STUDENT_NAME+ " TEXT, " +
            BIRTDAY + " TEXT, " +
            CLASS_NAME +" TEXT) ";
    private  String SQLQUERY_CLASS = " CREATE TABLE " + TABLE_CLASS + " ( " +
            ID + " integer primary key, " +
            CLASS_CODE + " TEXT," +
            CLASS_NAME + " TEXT)";
    private  static int VERSION = 1;
    public StudentDBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQUERY_CLASS);
        db.execSQL(SQLQUERY_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addClass(ClassRoom classRoom){
        SQLiteDatabase dbManager = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CLASS_CODE, classRoom.getClassCode());
        values.put(CLASS_NAME, classRoom.getClassname());
        dbManager.insert(TABLE_CLASS, null,values);
        dbManager.close();
    }
    public void addStudent(StudentClass studentClass){
        SQLiteDatabase dbManager = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, studentClass.getName());
        values.put(CLASS_NAME, studentClass.getClassRoom());
        values.put(BIRTDAY, studentClass.getBirthday());
        dbManager.insert(TABLE_STUDENT, null, values);
        dbManager.close();
    }
    public ArrayList<ClassRoom> getAllClass(){
        ArrayList <ClassRoom >  listClass = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CLASS;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
                ClassRoom classRoom = new ClassRoom();
                classRoom.setClassCode(cursor.getString(1));
                classRoom.setClassname(cursor.getString(2));
                listClass.add(classRoom);
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return listClass;
    }
    public ArrayList<StudentClass> getAllStudent(){
        ArrayList<StudentClass> listStudent = new ArrayList<>();
        String selectQuery= "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
                StudentClass studentClass = new StudentClass();
                studentClass.setName(cursor.getString(1));
                studentClass.setBirthday(cursor.getString(2));
                studentClass.setClassRoom(cursor.getString(3));
                listStudent.add(studentClass);
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return listStudent;
    }
}
