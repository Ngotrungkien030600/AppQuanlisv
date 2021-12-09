package com.example.appquanlisv.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import androidx.annotation.Nullable;

import com.example.appquanlisv.model.Student;
import com.example.appquanlisv.model.Subject;

public class database extends SQLiteOpenHelper {

    //ten database
    private static String DATABASE_NAME = "studentmanagement";
    //bang mon hoc
    private static String TABLE_SUBJECT = "subject";
    private static String ID_SUBJECT = "idsubjet";
    private static String SUBJECT_TITLE = "subjecttitle";
    private static String CREDITS = "credits";
    private static String TIME = "time";
    private static String PLACE = "place";
    private static int VERSION = 1;

    //bang sinh vien
    private static String TABLE_STUDENT = "student";
    private static String ID_STUDENT = "idstudent";
    private static String STUDEN_NAME = "studentname";
    private static String SEX = "sex";
    private static String STUDEN_CODE = "studentcode";
    private static String DATE_OF_BIRTH = "dateofbirth";


    //tao bang mon hoc
    private String SQLQuery;

    {
        SQLQuery = "CREATE TABLE " + TABLE_SUBJECT + " ( " + ID_SUBJECT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SUBJECT_TITLE + " TEXT,"
                + CREDITS + " INTEGER, "
                + TIME + " TEXT,"
                + PLACE + " TEXT)";
    }

    //tao bang sinh vien
    public String SQLQuery1;
    {
        SQLQuery1 = "CREATE TABLE " + TABLE_STUDENT +" ( " + ID_STUDENT + " integer primary key AUTOINCREMENT, "
                +STUDEN_NAME+ " TEXT,"
                +SEX+" TEXT,"
                +STUDEN_CODE+" TEXT,"
                +DATE_OF_BIRTH+" TEXT,"
                +ID_SUBJECT+" INTEGER , FOREIGN KEY ("+ ID_SUBJECT +" ) REFERENCES "+
                TABLE_SUBJECT+"("+ID_SUBJECT+"))";
    }



    public database(@Nullable Context context) {
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {

        sqliteDatabase.execSQL(SQLQuery);
        sqliteDatabase.execSQL(SQLQuery1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, int i, int i1) {

    }

    //phuong thuc insert subject
    public void AddSubjects(Subject subject){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SUBJECT_TITLE,subject.getSubject_title());
        values.put(CREDITS,subject.getNumber_of_credit());
        values.put(TIME,subject.getTime());
        values.put(PLACE,subject.getPlace());

        db.insert(TABLE_SUBJECT,null,values);
        db.close();
    }

    //cap nhat mon hoc
    public boolean UpdateSubject(Subject subject,int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBJECT_TITLE,subject.getSubject_title());
        values.put(CREDITS,subject.getNumber_of_credit());
        values.put(TIME,subject.getTime());
        values.put(PLACE,subject.getPlace());

        db.update(TABLE_SUBJECT,values, ID_SUBJECT+" = "+id,null);
        return true;
    }
    //lay du lieu mon hoc
    public Cursor getDataSubject(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SUBJECT,null);
        return cursor;
    }
    public int DeleteSubject(int i){
        //chu y getWritableDatabase();la ca doc ca gi
        //getReadableDatabase(); chi doc khong dc phep chinh suaw,khong can thiet thi read cho toi uu
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_SUBJECT,ID_SUBJECT+" = "+i,null );
        return res;
    }

    //dung de xoa xoa cac student cua subject da bi xoa
    public int DeleteSubjectStudent(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT,ID_SUBJECT+" = "+i,null);
        return res;

    }
    //insert student
    public void AddStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDEN_NAME,student.getStudent_name());
        values.put(SEX,student.getSex());
        values.put(STUDEN_CODE,student.getStudent_code());
        values.put(DATE_OF_BIRTH,student.getDate_of_birth());
        //values.put(ID_STUDENT,student.getId_student());
        //id student
        values.put(ID_SUBJECT, student.getId_subject());

        // SUA KHUC NAY
        db.insert(TABLE_STUDENT,null,values);
        db.close();

    }
    //lay tat ca sinh vien thuoc mon hoc do
    public Cursor getDataStudent(int id_subject){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " +TABLE_STUDENT+ " WHERE "+ID_SUBJECT+" = "+id_subject,null);
        return res;
    }

    //xoa student
    public int DeleteStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT,ID_STUDENT+" = "+id,null);
        return res;
    }

    // cap nhat sinh vien
    public boolean UpdateStudent(Student student, int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDEN_NAME,student.getStudent_name());
        values.put(SEX,student.getSex());
        values.put(STUDEN_CODE,student.getStudent_code());
        values.put(DATE_OF_BIRTH,student.getDate_of_birth());

        db.update(TABLE_STUDENT,values,ID_STUDENT+" = "+id,null);

        return true;
    }
}
