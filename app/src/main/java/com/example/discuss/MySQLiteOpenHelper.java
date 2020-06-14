package com.example.discuss;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import androidx.annotation.Nullable;

import java.io.File;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
//    public static final String DATABASE ="userInformation";
    public static final String PATH = Environment.getExternalStorageDirectory().getPath()+"/SQLite/";
    public static final int DBVERSION=1;

    public MySQLiteOpenHelper(@Nullable Context context) {
        super(context, "DATABASE", null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        File dir=new File(PATH);
        if(!dir.exists()){
            dir.mkdir();
        }
        try {
            db.execSQL("create table if not exists stuInformation (Id integer primary key,Photo int,Name text,Number text,Password text,School text)");
            db.execSQL("create table if not exists teaInformation (Id integer primary key,Photo int,Name text,Number text,Password text,School text)");
            db.execSQL("create table if not exists course (Id integer primary key,Course text,TNumber text)");
            db.execSQL("create table if not exists selection (Ideger int primary key,Course text,SNumber text)");

            db.execSQL("create table if not exists themes " +
                    "(Id integer primary key,Theme text,Content text,Publisher text,PublishTime DateTime,answerNum int,ThumbUpNum int,ZhuanNum int,Course int)");
            db.execSQL("create table if not exists answers " +
                    "(Id integer primary key,ThemeId int,Content text,Publisher text,PublishTime DateTime,ThumbUpNum int,IsTeacher Boolean)");
            db.execSQL("create table if not exists secondAnswers " +
                    "(Id integer primary key,AnswerId int,Content text,Publisher text,Accepter text)");
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            String sql="drop table if exists stuInformation";
            db.execSQL(sql);
            onCreate(db);
        }
    }
}
