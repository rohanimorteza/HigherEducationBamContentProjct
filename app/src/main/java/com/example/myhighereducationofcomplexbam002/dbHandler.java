package com.example.myhighereducationofcomplexbam002;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myhighereducationofcomplexbam002.Model.Faculty;

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


    public Faculty display2(String id){

        Cursor cursor= db.rawQuery("select * from tbl_faculty where id_prof="+id,null);
        cursor.moveToFirst();

            Faculty faculty = new Faculty();
            faculty.setId(cursor.getString(0));
            faculty.setFaculty_name(cursor.getString(1));
            faculty.setAx(cursor.getBlob(2));


        return faculty;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
