package com.example.discuss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class personalActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Intent intent;
    private Bundle bundle;

    private ImageButton back;


    private Button my_theme;
    private Button my_answer;
    private Button answer_me;

    private Button delete;
    private Button modify;
    private Button answer;
    private List<Theme> privateThemeList=new ArrayList<Theme>();
    private List<Answer> privateAnswerList=new ArrayList<Answer>();

    private String username;
    private myThemesAdapter adapter;
    private ListView listView;
    private String privatetheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        my_theme=(Button)findViewById(R.id.my_theme);
        my_answer=(Button)findViewById(R.id.my_answer);
        answer_me=(Button)findViewById(R.id.answer_me);
        listView=(ListView)findViewById(R.id.privatelist);

        back=(ImageButton)findViewById(R.id.back);

//        View view = View.inflate( this, R.layout.mywords, null);

        delete=(Button) findViewById(R.id.delete);
        modify=(Button) findViewById(R.id.modify);

        bundle=this.getIntent().getExtras();
        username=bundle.getString("username");

        my_theme.setOnClickListener(this);
        my_answer.setOnClickListener(this);
        answer_me.setOnClickListener(this);
        back.setOnClickListener(this);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.my_theme){

            initmyThemes(username);
            myThemesAdapter adapter=new myThemesAdapter(personalActivity.this,R.layout.mywords,privateThemeList);
            ListView listview = (ListView) findViewById(R.id.privatelist);
            listview.setAdapter(adapter);

        }else if(v.getId()==R.id.my_answer){

            initmyAnswers(username);
            myAnswersAdapter adapter=new myAnswersAdapter(personalActivity.this,R.layout.mywords,privateAnswerList);
            ListView listview = (ListView) findViewById(R.id.privatelist);
            listview.setAdapter(adapter);

        }else if(v.getId()==R.id.answer_me){

            initAnswerMe(username);
            answerMeAdapter adapter=new answerMeAdapter(personalActivity.this,R.layout.answerme,privateAnswerList);
            ListView listview = (ListView) findViewById(R.id.privatelist);
            listview.setAdapter(adapter);

        }else if(v.getId()==R.id.delete){
            deleteTheme(v.getTag().toString());
            intent=new Intent(personalActivity.this,personalActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else if(v.getId()==R.id.modify){
            //跳转到修改页面
            System.out.println("点击修改按钮");
            System.out.println("modify.getTag()"+v.getTag());
                intent=new Intent(personalActivity.this,modifyActivity.class);
                bundle.putString("modifyTheme", v.getTag().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            //modifyTheme();
        }else if(v.getId()==R.id.back){
            //跳转到修改页面
            System.out.println("点击返回按钮");
            System.out.println("delete.getTag()"+v.getTag());
            intent=new Intent(personalActivity.this,chiefActivity.class);
            bundle=getIntent().getExtras();
            intent.putExtras(bundle);
            startActivity(intent);
            //modifyTheme();
        }
    }

    public void initmyThemes(String username) {
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(personalActivity.this);
        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        privateThemeList.clear();
        Cursor cursor = myDatabase.query("themes", null, "Publisher=?", new String[]{username}, null, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                String course=null;
                String theme = cursor.getString(1);
                String publishTime = cursor.getString(4);
                int courseId=cursor.getInt(8);

                Cursor cursor1 = myDatabase.query("course", new String[]{"Course"}, "Id=?", new String[]{String.valueOf(courseId)}, null, null, null, null);
                if (cursor1.moveToFirst()) {
                    course = cursor1.getString(0);
                }

                Theme one = new Theme(publishTime, theme, course);
                privateThemeList.add(one);
            }while(cursor.moveToNext());
        }else{
            Toast.makeText(this,"你还没有发表过哦！",Toast.LENGTH_LONG).show();
        }
    }


    public void initmyAnswers(String username) {
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(personalActivity.this);
        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        privateAnswerList.clear();
        Cursor cursor = myDatabase.query("answers", null, "Publisher=?", new String[]{username}, null, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                String theme=null;
                String answer = cursor.getString(2);
                String publishTime = cursor.getString(4);
                int themeId=cursor.getInt(1);

                Cursor cursor1 = myDatabase.query("themes", new String[]{"Theme"}, "Id=?", new String[]{String.valueOf(themeId)}, null, null, null, null);
                if (cursor1.moveToFirst()) {
                    theme = cursor1.getString(0);
                }

                Answer one = new Answer(publishTime, theme, answer);
                privateAnswerList.add(one);
            }while(cursor.moveToNext());
        }else{
            Toast.makeText(this,"你还没有发表过哦！",Toast.LENGTH_LONG).show();
        }
    }

    public void initAnswerMe(String username) {
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(personalActivity.this);
        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        privateAnswerList.clear();
        String answer = null;
        String publisherNumber ;
        String publishTime = null;
        int photo = 0;
        String publisher = null;
        Cursor cursor = myDatabase.query("themes", new String[]{"Id","Theme"}, "Publisher=?", new String[]{username}, null, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                int themeId=cursor.getInt(0);
                String theme=cursor.getString(1);
                Cursor cursor1 = myDatabase.query("answers", new String[]{"Content","Publisher","PublishTime"}, "ThemeId=?", new String[]{String.valueOf(themeId)}, null, null, null, null);
                if (cursor1.moveToFirst()) {
                    do{

                        answer= cursor1.getString(0);
                        publisherNumber = cursor1.getString(1);
                        publishTime = cursor1.getString(2);
                        Cursor cursor2=myDatabase.query("stuInformation", new String[]{"Photo","Name"}, "Number=?", new String[]{publisherNumber}, null, null, null, null);
                        if (cursor2.moveToFirst()) {
                           photo=cursor2.getInt(0);
                           publisher=cursor2.getString(1);
                        }
                    }while (cursor1.moveToNext());

                    Answer one = new Answer(photo,publisher,publishTime,theme,answer);
                    privateAnswerList.add(one);
                }

            }while(cursor.moveToNext());
        }else{
            Toast.makeText(this,"还没有人回复你，再等等！",Toast.LENGTH_LONG).show();
        }
    }

public void deleteTheme(String deleteTheme){
    MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
    SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
    String sql="delete from themes where Theme='"+deleteTheme+"'";
    System.out.println("SQL:"+sql);
    myDatabase.execSQL(sql);
    System.out.println("点击删除按钮");
}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Theme chooseTheme=privateThemeList.get(position);
        privatetheme=chooseTheme.getTheme();
       intent=new Intent(personalActivity.this,modifyActivity.class);
       bundle.putString("privatetheme",privatetheme);
       intent.putExtras(bundle);
       startActivity(intent);
    }
}
