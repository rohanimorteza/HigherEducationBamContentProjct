package com.example.myhighereducationofcomplexbam002;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myhighereducationofcomplexbam002.Model.Faculty;
import com.example.myhighereducationofcomplexbam002.Model.Professor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class dbHandler extends SQLiteOpenHelper {

    public static String DBNAME="bamuniversity.db";
    public static String DBPATH="";
    public static String TBL="tbl_faculty";
    private Context cntx;
    SQLiteDatabase db;


    public dbHandler(Context context) {
        super(context, DBNAME, null, 1);
        cntx = context;
        DBPATH = context.getCacheDir().getPath()+"/"+DBNAME;
    }

    public boolean DbExist(){

        File f = new File(DBPATH);
        if(f.exists()){
            return true;

        }else {
            return false;
        }
    }

    public boolean CopyDb(){
        try{

            FileOutputStream out = new FileOutputStream(DBPATH);
            InputStream in = cntx.getAssets().open(DBNAME);

            byte[] buffer = new byte[1024];
            int ch;

            while ((ch=in.read(buffer))>0){

                out.write(buffer,0,ch);
            }
            out.flush();
            out.close();
            in.close();
            return true;

        }catch (Exception e){
            return false;
        }
    }


    public void open(){

        if(DbExist()){

            try {
                File f = new File(DBPATH);
                db = SQLiteDatabase.openDatabase(DBPATH,null,SQLiteDatabase.OPEN_READWRITE);
            }catch (Exception e){
                e.printStackTrace();
            }


        }else {
            if(CopyDb()){
                open();
            }
        }

    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public String get_Name(int id){

       Cursor cursor= db.rawQuery("select * from tbl_faculty where id="+id,null);
       cursor.moveToFirst();
       return cursor.getString(1);
    }

    public List<Faculty> display_faculty(){

        Cursor cursor= db.rawQuery("select * from tbl_faculty ",null);
        cursor.moveToFirst();
        List<Faculty> facultyList = new ArrayList<>();

        do{
            Faculty faculty = new Faculty();
            faculty.setId(cursor.getString(0));
            faculty.setFaculty_name(cursor.getString(1));
            faculty.setAx(cursor.getBlob(2));

            facultyList.add(faculty);

        }while (cursor.moveToNext());

        return facultyList;

    }

    public List<Faculty> display_professor_list(){

        Cursor cursor= db.rawQuery("select * from tbl_professor ",null);
        cursor.moveToFirst();
        List<Faculty> professorList = new ArrayList<>();

        do{
            Faculty professor = new Faculty();
            professor.setId(cursor.getString(0));
            professor.setFaculty_name(cursor.getString(1));
            professor.setAx(cursor.getBlob(2));

            professorList.add(professor);

        }while (cursor.moveToNext());

        return professorList;

    }


    public Faculty display2(String id){

        Cursor cursor= db.rawQuery("select * from tbl_professor where id_prof="+id,null);
        cursor.moveToFirst();

            Faculty faculty = new Faculty();
            faculty.setId(cursor.getString(0));
            faculty.setFaculty_name(cursor.getString(1));
            faculty.setAx(cursor.getBlob(2));

        return faculty;

    }

    public Professor getProfessorDetail(String id){

        Professor professor = new Professor();
        Cursor cursor= db.rawQuery("select id_prof , name_prof , ax_prof , name from tbl_professor A " +
                "INNER JOIN tbl_faculty B " +
                "ON B.id=A.id_faculty " +
                "where A.id_prof="+id,null);
        cursor.moveToFirst();


        professor.setIdProf(cursor.getString(0));
        professor.setNameProf(cursor.getString(1));
        professor.setAxProf(cursor.getBlob(2));
        professor.setFacultyProf(cursor.getString(3));
        return professor;

    }

    public int ProfessorCount(){
        Cursor cursor= db.rawQuery("select * from tbl_professor ",null);
        cursor.moveToFirst();

        return cursor.getCount();
    }

    public int facultyCount(){
        Cursor cursor= db.rawQuery("select * from tbl_faculty ",null);
        cursor.moveToFirst();

        return cursor.getCount();
    }
    public String get_faculty_name(String id){
        Cursor cursor= db.rawQuery("select * from tbl_faculty where id="+id,null);
        cursor.moveToFirst();

        return cursor.getString(1);
    }

    public String get_Cat_Prof(String id){
        Cursor cursor= db.rawQuery("select * from tbl_professor where id_prof="+id,null);
        cursor.moveToFirst();

        return cursor.getString(3);
    }

    public void insert(String av){
        ContentValues cv = new ContentValues();
        cv.put("average",av);

        db.insert("tbl_average","average",cv);
    }


    public boolean isDisplay(String a){
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_professor where name_prof like '%"+a+"%'",null);
        cursor.moveToFirst();
        return cursor.getCount()>0;
    }

    public List<Faculty> display_search(String a)  {
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_professor where name_prof like '%"+a+"%'",null);
        cursor.moveToFirst();
        List<Faculty> professorList = new ArrayList<>();
        do{
            Faculty professor = new Faculty();
            professor.setId(cursor.getString(0));
            professor.setFaculty_name(cursor.getString(1));
            professor.setAx(cursor.getBlob(2));

            professorList.add(professor);
        }while (cursor.moveToNext());
        return professorList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
