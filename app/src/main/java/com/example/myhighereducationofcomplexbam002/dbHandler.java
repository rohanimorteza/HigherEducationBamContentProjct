package com.example.myhighereducationofcomplexbam002;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

public class dbHandler extends SQLiteOpenHelper {

    public static String DBNAME="bamuniversity.db";
    public static String DBPATH="";
    public static String TBL="tbl_faculty";
    private Context cntx;


    public dbHandler(Context context) {
        super(context, "BAM", null, 1);
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

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
