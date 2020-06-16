package com.example.discuss;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.List;

public class AnswerOrderHelper {
//    private List<Theme> themeList=new ArrayList<Theme>();
    public void initAnswers(Context context, List<Answer> answerList,String theme) {

        //查询themes表显示所有内容
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = myDatabase.query("themes", new String[]{"Id"}, "Theme=?", new String[]{theme}, null, null, null, null);
        if(cursor.moveToFirst()) {
            int themeId=cursor.getInt(0);
            Cursor cursor1 = myDatabase.query("answers", null, "ThemeId=?", new String[]{String.valueOf(themeId)}, null, null, null, null);
            if(cursor1.moveToFirst()) {
            do {
                String content = cursor1.getString(2);
                String publisher = cursor1.getString(3);
                String publishTime = cursor1.getString(4);
                int thumbUpNum = cursor1.getInt(5);
                int isTeacher = cursor1.getInt(6);
                //根据themes中publisher（学号）查学生姓名
                Cursor cursor2 = myDatabase.query("stuInformation", new String[]{"Name"}, "Number=?", new String[]{publisher}, null, null, null, null);
                if (cursor2.moveToFirst()) {
                    publisher = cursor2.getString(0);
                }

                Answer one = new Answer(R.drawable.photo, publisher, publishTime, content, thumbUpNum);
                answerList.add(one);
            }while(cursor1.moveToNext());
            }
        }else{
            Toast.makeText(context,"暂无讨论内容，快去发表吧",Toast.LENGTH_LONG).show();
        }
    }

    public void initThemesByTimeDcs(Context context, List<Theme> themeList) {

        //查询themes表显示所有内容
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = myDatabase.query("themes", null, null, null, null, null, "PublishTime desc", null);
        if(cursor.moveToFirst()) {

            do {
                String theme = cursor.getString(1);
                String publisher = cursor.getString(3);
                String publishTime = cursor.getString(4);
                int answerNum = cursor.getInt(5);
                int thumbUpNum = cursor.getInt(6);
                int zhuanNum = cursor.getInt(7);
                //根据themes中publisher（学号）查学生姓名
                Cursor cursor1 = myDatabase.query("stuInformation", new String[]{"Name"}, "Number=?", new String[]{publisher}, null, null, null, null);
                if (cursor1.moveToFirst()) {
                    publisher = cursor1.getString(0);
                }

                Theme one = new Theme(R.drawable.photo, publisher, publishTime, theme, thumbUpNum, answerNum, zhuanNum);
                themeList.add(one);
            }while(cursor.moveToNext());
        }else{
            Toast.makeText(context,"暂无讨论内容，快去发表吧",Toast.LENGTH_LONG).show();
        }
    }

    public void initThemesByAnswerDcs(Context context, List<Theme> themeList) {

        //查询themes表显示所有内容
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = myDatabase.query("themes", null, null, null, null, null, "answerNum desc", null);
        if(cursor.moveToFirst()) {

            do {
                String theme = cursor.getString(1);
                String publisher = cursor.getString(3);
                String publishTime = cursor.getString(4);
                int answerNum = cursor.getInt(5);
                int thumbUpNum = cursor.getInt(6);
                int zhuanNum = cursor.getInt(7);
                //根据themes中publisher（学号）查学生姓名
                Cursor cursor1 = myDatabase.query("stuInformation", new String[]{"Name"}, "Number=?", new String[]{publisher}, null, null, null, null);
                if (cursor1.moveToFirst()) {
                    publisher = cursor1.getString(0);
                }

                Theme one = new Theme(R.drawable.photo, publisher, publishTime, theme, thumbUpNum, answerNum, zhuanNum);
                themeList.add(one);
            }while(cursor.moveToNext());
        }else{
            Toast.makeText(context,"暂无讨论内容，快去发表吧",Toast.LENGTH_LONG).show();
        }
    }

    public void initThemesByThumbDcs(Context context, List<Theme> themeList) {

        //查询themes表显示所有内容
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = myDatabase.query("themes", null, null, null, null, null, "ThumbUpNum desc", null);
        if(cursor.moveToFirst()) {

            do {
                String theme = cursor.getString(1);
                String publisher = cursor.getString(3);
                String publishTime = cursor.getString(4);
                int answerNum = cursor.getInt(5);
                int thumbUpNum = cursor.getInt(6);
                int zhuanNum = cursor.getInt(7);
                //根据themes中publisher（学号）查学生姓名
                Cursor cursor1 = myDatabase.query("stuInformation", new String[]{"Name"}, "Number=?", new String[]{publisher}, null, null, null, null);
                if (cursor1.moveToFirst()) {
                    publisher = cursor1.getString(0);
                }

                Theme one = new Theme(R.drawable.photo, publisher, publishTime, theme, thumbUpNum, answerNum, zhuanNum);
                themeList.add(one);
            }while(cursor.moveToNext());
        }else{
            Toast.makeText(context,"暂无讨论内容，快去发表吧",Toast.LENGTH_LONG).show();
        }
    }
}
