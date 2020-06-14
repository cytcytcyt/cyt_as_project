package com.example.discuss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class publishActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton publish;
    private ImageButton back;
    private EditText course;
    private EditText theme;
    private EditText content;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        publish=(ImageButton)findViewById(R.id.publish);
        back=(ImageButton)findViewById(R.id.back);

        course=(EditText)findViewById(R.id.course);
        System.out.println("course"+course);
        theme=(EditText)findViewById(R.id.discussTitle);
        content=(EditText)findViewById(R.id.discussContent);


        Bundle bundle=this.getIntent().getExtras();
        username=bundle.getString("username");
        System.out.println("开始发布 username"+username);


        publish.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.out.println("click");
        switch (v.getId()) {
            case R.id.publish:
            System.out.println("start publish");
            MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(publishActivity.this);
            SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Theme", theme.getText().toString());
            values.put("Content", content.getText().toString());
            values.put("Publisher", username);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//获取当前时间
            Date date = new Date(System.currentTimeMillis());
            values.put("PublishTime",simpleDateFormat.format(date));
            System.out.println("date:"+date.toString());
            values.put("answerNum", 0);
            values.put("ThumbUpNum", 0);
            values.put("ZhuanNum", 0);
            Cursor cursor= myDatabase.query("course", new String[]{"Id"}, "Course=?", new String[]{course.getText().toString()}, null, null, null, null);
            if (cursor.moveToFirst())
                values.put("Course",cursor.getInt(0));
            System.out.println("Course"+cursor.getInt(0));
            myDatabase.insert("themes", null, values);
//            Intent intent=new Intent(publishActivity.this,chiefActivity.class);
//            startActivity(intent);


            case R.id.back:
            Intent intent=new Intent(publishActivity.this,chiefActivity.class);
            Bundle bundle=getIntent().getExtras();
            intent.putExtras(bundle);
            startActivity(intent);
            break;

            default:
                break;
        }
    }
}
